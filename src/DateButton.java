/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ganesh Sharma
 */

import java.awt.event.*;
import java.text.*;
import java.util.*;
import javax.swing.*;

public class DateButton extends JButton {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final DateChooser DATE_CHOOSER = new DateChooser((JFrame) null, "Select Date");
    private Date date;

    protected void fireActionPerformed(ActionEvent e) {
        Date newDate = DATE_CHOOSER.select(date);
        if (newDate == null) {
            return;
        }
        setDate(newDate);
    }

    public DateButton(Date date) {
        super(DATE_FORMAT.format(date));
        this.date = date;
    }

    public DateButton() {
        this(new Date());
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        Date old = this.date;
        this.date = date;
        setText(DATE_FORMAT.format(date));
        firePropertyChange("date", old, date);
    }
}
