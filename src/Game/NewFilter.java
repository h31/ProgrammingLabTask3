package Game;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

class NewFilter extends DocumentFilter{
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        string = string.replaceAll("[\\D]", "");
        super.insertString(fb, offset, string, attr);
    }

    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        text = text.replaceAll("[\\D]", "");
        super.replace(fb, offset, length, text, attrs);
    }
}