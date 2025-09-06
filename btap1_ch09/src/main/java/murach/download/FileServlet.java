package murach.download;



import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import murach.download.DownloadItem;

public class FileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("name");
        if (name == null || name.isBlank()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // chỉ cho phép các file có trong catalog
        ServletContext sc = getServletContext();
        @SuppressWarnings("unchecked")
        List<DownloadItem> items = (List<DownloadItem>) sc.getAttribute("downloads");
        boolean allowed = items != null && items.stream().anyMatch(i -> name.equals(i.getFilename()));
        if (!allowed) { resp.sendError(HttpServletResponse.SC_NOT_FOUND); return; }

        String path = "/files/" + name;
        URL resourceUrl = sc.getResource(path);
        if (resourceUrl == null) { resp.sendError(HttpServletResponse.SC_NOT_FOUND); return; }

        URLConnection conn = resourceUrl.openConnection();
        long fileLength = conn.getContentLengthLong(); // hoạt động cả khi WAR nén (JarURLConnection)
        String mime = sc.getMimeType(name);
        if (mime == null) mime = "audio/mpeg";
        resp.setContentType(mime);
        resp.setHeader("Accept-Ranges", "bytes");
        resp.setHeader("X-Content-Type-Options", "nosniff");

        // Content-Disposition: inline (nghe), ?dl=1 => attachment (tải)
        String disp = "1".equals(req.getParameter("dl")) ? "attachment" : "inline";
        String encoded = URLEncoder.encode(name, StandardCharsets.UTF_8).replace("+", "%20");
        resp.setHeader("Content-Disposition", disp + "; filename*=UTF-8''" + encoded);

        // Parse Range header: e.g. "bytes=START-" or "bytes=START-END"
        String range = req.getHeader("Range");
        long start = 0, end = fileLength - 1;
        boolean isPartial = false;

        if (range != null && range.startsWith("bytes=") && fileLength > 0) {
            try {
                String[] parts = range.substring(6).split("-", 2);
                if (!parts[0].isEmpty()) start = Long.parseLong(parts[0]);
                if (parts.length > 1 && !parts[1].isEmpty()) end = Long.parseLong(parts[1]);
                if (end >= fileLength) end = fileLength - 1;
                if (start > end || start < 0) {
                    resp.setHeader("Content-Range", "bytes */" + fileLength);
                    resp.sendError(HttpServletResponse.SC_REQUESTED_RANGE_NOT_SATISFIABLE);
                    return;
                }
                isPartial = true;
            } catch (NumberFormatException ignore) {}
        }

        long contentLength = end - start + 1;
        if (isPartial) {
            resp.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
            resp.setHeader("Content-Range", "bytes " + start + "-" + end + "/" + fileLength);
        }
        if (contentLength >= 0) resp.setContentLengthLong(contentLength);

        try (InputStream in = resourceUrl.openStream();
             OutputStream out = resp.getOutputStream()) {

            // skip tới vị trí start
            if (start > 0) {
                long skipped = 0;
                while (skipped < start) {
                    long s = in.skip(start - skipped);
                    if (s <= 0) break;
                    skipped += s;
                }
            }

            // ghi đúng số byte yêu cầu
            byte[] buf = new byte[8192];
            long remaining = contentLength;
            while (remaining > 0) {
                int read = in.read(buf, 0, (int) Math.min(buf.length, remaining));
                if (read == -1) break;
                out.write(buf, 0, read);
                remaining -= read;
            }
        }
    }
}
