/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ganesh Sharma
 */

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class DateChooser extends JDialog
        implements ItemListener, MouseListener, FocusListener, KeyListener, ActionListener {

    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String[] MONTHS =
            new String[]{
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    };
    private static final String[] DAYS =
            new String[]{
        "Sun",
        "Mon",
        "Tue",
        "Wed",
        "Thu",
        "Fri",
        "Sat"
    };
    private static final Color WEEK_DAYS_FOREGROUND = Color.black;
    private static final Color DAYS_FOREGROUND = Color.blue;
    private static final Color SELECTED_DAY_FOREGROUND = Color.white;
    private static final Color SELECTED_DAY_BACKGROUND = Color.blue;
    private static final Border EMPTY_BORDER = BorderFactory.createEmptyBorder(1, 1, 1, 1);
    private static final Border FOCUSED_BORDER = BorderFactory.createLineBorder(Color.yellow, 1);
    private static final int FIRST_YEAR = 2000;
    private static final int LAST_YEAR = 2100;
    private GregorianCalendar calendar;
    private JLabel[][] days;
    private FocusablePanel daysGrid;
    private JComboBox month;
    private JComboBox year;
    private JButton ok;
    private JButton cancel;
    private int offset;
    private int lastDay;
    private JLabel day;
    private boolean okClicked;

    private static class FocusablePanel extends JPanel {

        public FocusablePanel(LayoutManager layout) {
            super(layout);

        }

        public boolean isFocusTraversable() {
            return true;
        }
    }

    private void construct() {
        calendar = new GregorianCalendar();

        month = new JComboBox(MONTHS);
        month.addItemListener(this);

        year = new JComboBox();
        for (int i = FIRST_YEAR; i <= LAST_YEAR; i++) {
            year.addItem(Integer.toString(i));
        }
        year.addItemListener(this);

        days = new JLabel[7][7];
        for (int i = 0; i < 7; i++) {
            days[0][i] = new JLabel(DAYS[i], JLabel.RIGHT);
            days[0][i].setForeground(WEEK_DAYS_FOREGROUND);
        }
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                days[i][j] = new JLabel(" ", JLabel.RIGHT);
                days[i][j].setForeground(DAYS_FOREGROUND);
                days[i][j].setBackground(SELECTED_DAY_BACKGROUND);
                days[i][j].setBorder(EMPTY_BORDER);
                days[i][j].addMouseListener(this);
            }
        }

        ok = new JButton("Ok");
        ok.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);

        JPanel monthYear = new JPanel();
        monthYear.add(month);
        monthYear.add(year);

        daysGrid = new FocusablePanel(new GridLayout(7, 7, 5, 0));
        daysGrid.addFocusListener(this);
        daysGrid.addKeyListener(this);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                daysGrid.add(days[i][j]);
            }
        }
        daysGrid.setBackground(Color.white);
        daysGrid.setBorder(BorderFactory.createLoweredBevelBorder());
        JPanel daysPanel = new JPanel();
        daysPanel.add(daysGrid);

        JPanel buttons = new JPanel();
        buttons.add(ok);
        buttons.add(cancel);

        Container dialog = getContentPane();
        dialog.add("North", monthYear);
        dialog.add("Center", daysPanel);
        dialog.add("South", buttons);

        pack();
        setResizable(false);
    }

    private int getSelectedDay() {
        if (day == null) {
            return -1;
        }
        try {
            return Integer.parseInt(day.getText());
        } catch (NumberFormatException e) {
        }
        return -1;
    }

    private void setSelected(JLabel newDay) {
        if (day != null) {
            day.setForeground(DAYS_FOREGROUND);
            day.setOpaque(false);
            day.setBorder(EMPTY_BORDER);
        }
        day = newDay;
        day.setForeground(SELECTED_DAY_FOREGROUND);
        day.setOpaque(true);
        if (daysGrid.hasFocus()) {
            day.setBorder(FOCUSED_BORDER);
        }
    }

    private void setSelected(int newDay) {
        setSelected(days[(newDay + offset - 1) / 7 + 1][(newDay + offset - 1) % 7]);
    }

    private void update() {
        int iday = getSelectedDay();
        for (int i = 0; i < 7; i++) {
            days[1][i].setText(" ");
            days[5][i].setText(" ");
            days[6][i].setText(" ");
        }
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month.getSelectedIndex() + Calendar.JANUARY);
        calendar.set(Calendar.YEAR, year.getSelectedIndex() + FIRST_YEAR);

        offset = calendar.get(Calendar.DAY_OF_WEEK) - Calendar.SUNDAY;
        lastDay = calendar.getActualMaximum(Calendar.DATE);
        for (int i = 0; i < lastDay; i++) {
            days[(i + offset) / 7 + 1][(i + offset) % 7].setText(String.valueOf(i + 1));
        }
        if (iday != -1) {
            if (iday > lastDay) {
                iday = lastDay;
            }
            setSelected(iday);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ok) {
            okClicked = true;
        }
        hide();
    }

    public void focusGained(FocusEvent e) {
        setSelected(day);
    }

    public void focusLost(FocusEvent e) {
        setSelected(day);
    }

    public void itemStateChanged(ItemEvent e) {
        update();
    }

    public void keyPressed(KeyEvent e) {
        int iday = getSelectedDay();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (iday > 1) {
                    setSelected(iday - 1);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (iday < lastDay) {
                    setSelected(iday + 1);
                }
                break;
            case KeyEvent.VK_UP:
                if (iday > 7) {
                    setSelected(iday - 7);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (iday <= lastDay - 7) {
                    setSelected(iday + 7);
                }
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        JLabel day = (JLabel) e.getSource();
        if (!day.getText().equals(" ")) {
            setSelected(day);
        }
        daysGrid.requestFocus();
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public DateChooser(Dialog owner, String title) {
        super(owner, title, true);
        construct();
    }

    public DateChooser(Dialog owner) {
        super(owner, true);
        construct();
    }

    public DateChooser(Frame owner, String title) {
        super(owner, title, true);
        construct();
    }

    public DateChooser(Frame owner) {
        super(owner, true);
        construct();
        setLocation((screen.width - 800) / 2, ((screen.height - 550) / 2));
    }

    public Date select(Date date) {
        calendar.setTime(date);
        int _day = calendar.get(Calendar.DATE);
        int _month = calendar.get(Calendar.MONTH);
        int _year = calendar.get(Calendar.YEAR);

        year.setSelectedIndex(_year - FIRST_YEAR);
        month.setSelectedIndex(_month - Calendar.JANUARY);
        setSelected(_day);
        okClicked = false;
        show();
        if (!okClicked) {
            return null;
        }
        calendar.set(Calendar.DATE, getSelectedDay());
        calendar.set(Calendar.MONTH, month.getSelectedIndex() + Calendar.JANUARY);
        calendar.set(Calendar.YEAR, year.getSelectedIndex() + FIRST_YEAR);
        return calendar.getTime();
    }

    public Date select() {
        return select(new Date());
    }
}

