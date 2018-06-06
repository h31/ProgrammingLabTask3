package GUI;

import javax.swing.*;

public class MSButton extends JButton {
    private String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return  name;
    }

    public boolean compareCell(String name) {
        char[] firstTag = this.getName().toCharArray();
        char[] secondTag = name.toCharArray();
        return Math.abs((int) (firstTag[0]) - (int) (secondTag[0]))
                + Math.abs((int) (firstTag[2]) - (int) (secondTag[2])) == 1;
    }
}
