package murach.tags;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.JspWriter;
import java.io.IOException;

public class IfEmptyMarkTag extends TagSupport {
  private String field;
  private String color = "green"; // default per exercise

  public void setField(String field) { this.field = field; }
  public void setColor(String color) { this.color = color; }

  @Override public int doStartTag() throws JspException {
    boolean empty = (field == null || field.trim().isEmpty());
    if (empty) {
      try {
        JspWriter out = pageContext.getOut();
        out.print("<span style='color:" + color + "'>*</span>");
      } catch (IOException e) {
        throw new JspException(e);
      }
    }
    return SKIP_BODY;
  }
}
