package murach.download;

import java.io.Serializable;

public class DownloadItem implements Serializable {
    private String code;
    private String title;
    private String filename;

    public DownloadItem() {}
    public DownloadItem(String code, String title, String filename) {
        this.code = code;
        this.title = title;
        this.filename = filename;
    }

    public String getCode() { return code; }
    public void setCode(String v) { this.code = v; }

    public String getTitle() { return title; }
    public void setTitle(String v) { this.title = v; }

    public String getFilename() { return filename; }
    public void setFilename(String v) { this.filename = v; }
}
