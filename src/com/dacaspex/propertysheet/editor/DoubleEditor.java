package com.dacaspex.propertysheet.editor;

import com.dacaspex.propertysheet.PropertySheet;
import com.dacaspex.propertysheet.property.Property;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DoubleEditor extends PropertySheetCellEditor implements KeyListener {

    private Property<Double> property;

    public DoubleEditor(Property<Double> property, PropertySheet sheet) {
        super(property, sheet, new JTextField());

        this.property = property;

        super.getComponent().addKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent event) {

        // Get value from editor component
        JTextField textField = (JTextField) super.getComponent();
        String value = textField.getText();

        // Validate input
        if (property.getValidator().validate(value)) {
            property.setValue(Double.parseDouble(value));
            textField.setBackground(sheet.getBackgroundColor());

            // Dispatch event to indicate something happened
            eventDispatcher.dispatchUpdateEvent(property);
        } else {
            textField.setBackground(sheet.getInvalidColor());
        }
    }

    @Override
    public void keyTyped(KeyEvent event) {
        return;
    }
}
