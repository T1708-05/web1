package murach.tags;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

public class CurrentDateTag extends TagSupport {
  @Override public int doStartTag() throws JspException {
    DateFormat fmt = DateFormat.getDateInstance(DateFormat.LONG);
    try {
      JspWriter out = pageContext.getOut();
      out.print(fmt.format(new Date()));
    } catch (IOException e) {
      throw new JspException(e);
    }
    return SKIP_BODY;
  }
}
