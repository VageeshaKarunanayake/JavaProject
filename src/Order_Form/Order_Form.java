/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Order_Form;

import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import javax.swing.JFileChooser;
import javax.swing.event.*;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

    
/**
 *
 * @author Vageesha
 */

public class Order_Form extends javax.swing.JFrame {

    /**
     * Creates new form Order_Form
     */
    public Order_Form() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Order_ID = new javax.swing.JLabel();
        Customer_ID = new javax.swing.JLabel();
        Product_ID = new javax.swing.JLabel();
        Quantity = new javax.swing.JLabel();
        Order_Files = new javax.swing.JLabel();
        Order_Status = new javax.swing.JLabel();
        Discount = new javax.swing.JLabel();
        Total_Price = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        Quantity_Input = new javax.swing.JTextField();
        DocumentListener documentListener = new DocumentListener() {
            public void changedUpdate(DocumentEvent documentEvent) {

            }
            public void insertUpdate(DocumentEvent documentEvent) {

                String qty = Quantity_Input.getText();
                String dis = Discount_Input.getText();
                String UnP = jLabel9.getText();
                Double UnPrice = Double.parseDouble(UnP);
                Double total = null;

                if((qty.equals("")) && (dis.equals("")))
                total = (Double) UnPrice;
                else if(dis.equals("")){
                    Integer qtyInt = Integer.parseInt(qty);
                    total = (Double) UnPrice * qtyInt;
                }
                else if(qty.equals("")){
                    Double disDou = Double.parseDouble(dis);
                    total = (Double) UnPrice * ((100 - disDou)/100);
                }

                else{
                    Integer qtyInt = Integer.parseInt(qty);
                    Double disDou = Double.parseDouble(dis);
                    total = (Double) UnPrice * qtyInt * ((100 - disDou)/100);
                }

                jLabel1.setText(Double.toString(total));

            }
            public void removeUpdate(DocumentEvent documentEvent) {

            }
        };

        Quantity_Input.getDocument().addDocumentListener(documentListener);
        Search_Button = new javax.swing.JButton();
        File = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Discount_Input = new javax.swing.JTextField();
        Discount_Input.getDocument().addDocumentListener(documentListener);
        Order_Status_Input = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(Order_Status_Input);
        jLabel3 = new javax.swing.JLabel();
        Add = new javax.swing.JButton();
        Reset = new javax.swing.JButton();
        Back = new javax.swing.JButton();
        Customer_Name_Input = new javax.swing.JComboBox<>();

        AutoCompleteDecorator.decorate(Customer_Name_Input);
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gold_database", "root" , "1234");
            PreparedStatement DBOUTPUT = conn.prepareStatement("SELECT name FROM customers");
            ResultSet RS = DBOUTPUT.executeQuery();	
            while(RS.next()){ 
                Customer_Name_Input.addItem(RS.getString("name"));
            }                                         
        }catch(Exception e) {
            System.out.println(e);
        }

        Customer_Name_Input.setSelectedIndex(-1);
        Customer_Name_Input.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent event) {

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    String txt = Customer_Name_Input.getSelectedItem().toString();
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gold_database", "root" , "1234");
                        PreparedStatement DBOUTPUT2 = conn.prepareStatement("SELECT address FROM customers WHERE name = ?");
                        DBOUTPUT2.setString(1, txt);
                        ResultSet RS2 = DBOUTPUT2.executeQuery();

                        if(RS2.next()) 
                        jLabel5.setText(RS2.getString("address"));
                    }catch(Exception e) {
                        System.out.println(e);
                    }
                }
            }
        });
        jLabel5 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        Order_Date_Input = new org.jdesktop.swingx.JXDatePicker();
        Order_Due_Date_Input = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Product_ID_Input = new javax.swing.JComboBox<>();
        AutoCompleteDecorator.decorate(Product_ID_Input);

        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gold_database", "root" , "1234");
            PreparedStatement DBOUTPUT3 = conn.prepareStatement("SELECT productID FROM products");
            ResultSet RS3 = DBOUTPUT3.executeQuery();
            while(RS3.next()){ 
                Product_ID_Input.addItem(RS3.getString("productID"));
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        Product_ID_Input.setSelectedIndex(-1);

        Product_ID_Input.addItemListener(new ItemListener() {

            public void itemStateChanged(ItemEvent event) {

                if (event.getStateChange() == ItemEvent.SELECTED) {
                    String txt = Product_ID_Input.getSelectedItem().toString();
                    int product = Integer.parseInt(txt);    
                    try {
                        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gold_database", "root" , "1234");
                        PreparedStatement DBOUTPUT4 = conn.prepareStatement("SELECT price FROM products WHERE productID = ?");
                        DBOUTPUT4.setInt(1, product);
                        ResultSet RS4 = DBOUTPUT4.executeQuery();
                        String price = null;

                        if(RS4.next())
                        price = RS4.getString("price");

                        jLabel9.setText(price);

                        String qty = Quantity_Input.getText();
                        String dis = Discount_Input.getText();
                        Double UnPrice = Double.parseDouble(price);
                        Double total = null; 

                        if((qty.equals("")) && (dis.equals("")))
                        total = (Double) UnPrice;
                        else if(dis.equals("")){
                            Integer qtyInt = Integer.parseInt(qty);
                            total = (Double) UnPrice * qtyInt;
                        }
                        else if(qty.equals("")){
                            Double disDou = Double.parseDouble(dis);
                            total = (Double) UnPrice * ((100 - disDou)/100);
                        }

                        else{
                            Integer qtyInt = Integer.parseInt(qty);
                            Double disDou = Double.parseDouble(dis);
                            total = (Double) UnPrice * qtyInt * ((100 - disDou)/100);
                        }

                        jLabel1.setText(Double.toString(total));

                    }catch(Exception e) {
                        System.out.println(e);
                    }
                }
            }
        });
        jLabel9 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gold_database", "root" , "1234");
            PreparedStatement DBOUTPUT5 = conn.prepareStatement("SELECT MAX(orderID) FROM orders");
            ResultSet RS5 = DBOUTPUT5.executeQuery();
            Integer num = null;

            if(RS5.next()){
                num =RS5.getInt(1);
            }
            if(num == 0){
                jLabel18.setText("1");
            }
            else{
                int number = num + 1;
                String n = Integer.toString(number);
                jLabel18.setText(n);
            }
        }catch(Exception e) {
            System.out.println(e);
        }
        Description = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Description_Input = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1220, 900));

        Order_ID.setText("Order ID");

        Customer_ID.setText("Customer Name");

        Product_ID.setText("Product ID");

        Quantity.setText("Quantity");

        Order_Files.setText("Order Files");

        Order_Status.setText("Order Status");

        Discount.setText("Discount");

        Total_Price.setText("Total Price");

        jLabel2.setText(":-");

        jLabel7.setText(":-");

        jLabel10.setText(":-");

        jLabel11.setText(":-");

        jLabel12.setText(":-");

        jLabel13.setText(":-");

        jLabel14.setText(":-");

        jLabel15.setText(":-");

        Quantity_Input.setToolTipText("");

        Search_Button.setText("Search");
        Search_Button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Search_ButtonActionPerformed(evt);
            }
        });

        File.setText("None Selected");

        jLabel1.setText("0.0");

        Order_Status_Input.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "No status", "Processing", "Packing", "Transport" }));

        jLabel3.setFont(new java.awt.Font("Tahoma", 3, 48)); // NOI18N
        jLabel3.setText("Order Form");

        Add.setText("Add");
        Add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddActionPerformed(evt);
            }
        });

        Reset.setText("Reset");
        Reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetActionPerformed(evt);
            }
        });

        Back.setText("Back");

        jLabel5.setText("No Customer selected");

        jButton6.setText("Add Customer");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel6.setText("Order Date");

        jLabel8.setText("Order Due Date ");

        jLabel9.setText("0.0");

        jLabel16.setText("Unit Price");

        jLabel17.setText(":-");

        Description.setText("Description");

        jLabel20.setText(":-");

        Description_Input.setColumns(20);
        Description_Input.setRows(5);
        jScrollPane1.setViewportView(Description_Input);

        jLabel19.setText(":-");

        jLabel21.setText(":-");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(216, 216, 216)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Discount, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Order_Status, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Order_Files, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Quantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Product_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Customer_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Order_ID, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Total_Price, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6)
                    .addComponent(Description, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel20))
                .addGap(99, 99, 99)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(484, 484, 484))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Product_ID_Input, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6))
                            .addComponent(Order_Status_Input, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Discount_Input, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(File, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Search_Button, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Quantity_Input, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Customer_Name_Input, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(Order_Date_Input, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                                .addGap(36, 36, 36)
                                .addComponent(jLabel8)
                                .addGap(27, 27, 27)
                                .addComponent(jLabel21)
                                .addGap(34, 34, 34)
                                .addComponent(Order_Due_Date_Input, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel17)
                                .addGap(77, 77, 77)
                                .addComponent(jLabel9))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(9, 9, 9)))
                        .addGap(348, 348, 348))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(Order_ID)
                        .addComponent(jLabel2))
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel20)
                        .addComponent(Description))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(362, 362, 362)
                        .addComponent(Discount)
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Total_Price)
                            .addComponent(jLabel15))
                        .addGap(133, 133, 133))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Order_Date_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Order_Due_Date_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel21)
                            .addComponent(jLabel6)
                            .addComponent(jLabel19))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Customer_Name_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(Customer_ID))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Product_ID_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Product_ID)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Quantity_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(Quantity))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Search_Button)
                            .addComponent(File)
                            .addComponent(jLabel12)
                            .addComponent(Order_Files))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Order_Status_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13)
                            .addComponent(Order_Status))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Discount_Input, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel16)
                                    .addComponent(jLabel17))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(Add, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Reset, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Back, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     
    
    private void Search_ButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Search_ButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        int result = fileChooser.showOpenDialog(this);
        File selectedFile = null;
        if (result == JFileChooser.APPROVE_OPTION) {
        selectedFile = fileChooser.getSelectedFile();
        File.setText(selectedFile.getAbsolutePath());
        }
              
    }//GEN-LAST:event_Search_ButtonActionPerformed

    private void AddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddActionPerformed
        // TODO add your handling code here:
        Integer orderID = Integer.parseInt(jLabel18.getText());
        String orderDes = Description_Input.getText();
        java.util.Date orderDate = Order_Date_Input.getDate();
        java.util.Date orderDueDate = Order_Due_Date_Input.getDate();
        String customerName = Customer_Name_Input.getSelectedItem().toString();
        String Address = jLabel5.getText();
        Integer productID = Integer.parseInt(Product_ID_Input.getSelectedItem().toString());
        String file = File.getText();
        String orderStatus = Order_Status_Input.getSelectedItem().toString();
        Double discount = Double.parseDouble(Discount_Input.getText());
        Double totalPrice = Double.parseDouble(jLabel1.getText());
        Double unitPrice = Double.parseDouble(jLabel9.getText());
               
         try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/gold_database", "root" , "1234");
            
            FileInputStream fis = null;
            PreparedStatement DBOUTPUT6 = conn.prepareStatement("INSERT INTO orders VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
            DBOUTPUT6.setInt(1, orderID);
            DBOUTPUT6.setString(2, orderDes);
            
            java.sql.Date d1= new java.sql.Date(orderDate.getTime());
            DBOUTPUT6.setDate(3,d1);
            
            java.sql.Date d2= new java.sql.Date(orderDueDate.getTime());
            DBOUTPUT6.setDate(4,d2);
            
            DBOUTPUT6.setString(5, customerName);
            DBOUTPUT6.setString(6, Address);
            DBOUTPUT6.setInt(7, productID);
            
            File image= new File(file);
            fis = new FileInputStream(image);
            DBOUTPUT6.setBinaryStream(8, (InputStream) fis, (int) (image.length()));
            
            DBOUTPUT6.setString(9, orderStatus);
            DBOUTPUT6.setDouble(10, discount);
            DBOUTPUT6.setDouble(11, totalPrice);
            DBOUTPUT6.setDouble(12, unitPrice);
            
            DBOUTPUT6.executeUpdate();
            
        }catch(Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_AddActionPerformed

    private void ResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetActionPerformed
        // TODO add your handling code here:
        Order_Date_Input.setDate(null);
        Order_Due_Date_Input.setDate(null);
        Customer_Name_Input.setSelectedIndex(-1);
        jLabel5.setText("No Customer selected");
        Product_ID_Input.setSelectedIndex(-1);
        Quantity_Input.setText("");
        File.setText("None Selected");
        Order_Status_Input.setSelectedIndex(-1);
        Discount_Input.setText("");
        jLabel1.setText("0.00");
        jLabel9.setText("0.00");        
             
        
    }//GEN-LAST:event_ResetActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Order_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Order_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Order_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Order_Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Order_Form().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Add;
    private javax.swing.JButton Back;
    private javax.swing.JLabel Customer_ID;
    private javax.swing.JComboBox<String> Customer_Name_Input;
    private javax.swing.JLabel Description;
    private javax.swing.JTextArea Description_Input;
    private javax.swing.JLabel Discount;
    private javax.swing.JTextField Discount_Input;
    private javax.swing.JLabel File;
    private org.jdesktop.swingx.JXDatePicker Order_Date_Input;
    private org.jdesktop.swingx.JXDatePicker Order_Due_Date_Input;
    private javax.swing.JLabel Order_Files;
    private javax.swing.JLabel Order_ID;
    private javax.swing.JLabel Order_Status;
    private javax.swing.JComboBox<String> Order_Status_Input;
    private javax.swing.JLabel Product_ID;
    private javax.swing.JComboBox<String> Product_ID_Input;
    private javax.swing.JLabel Quantity;
    private javax.swing.JTextField Quantity_Input;
    private javax.swing.JButton Reset;
    private javax.swing.JButton Search_Button;
    private javax.swing.JLabel Total_Price;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
