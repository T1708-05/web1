package murach.tags;

import jakarta.servlet.jsp.tagext.TagSupport;
import jakarta.servlet.jsp.JspException;
import java.util.Calendar;

public class IfWeekdayTag extends TagSupport {
  @Override public int doStartTag() throws JspException {
    int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
    boolean weekend = (day == Calendar.SATURDAY || day == Calendar.SUNDAY);
    return weekend ? SKIP_BODY : EVAL_BODY_INCLUDE;
  }
}
