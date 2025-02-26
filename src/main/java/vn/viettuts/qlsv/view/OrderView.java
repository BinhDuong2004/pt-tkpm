/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vn.viettuts.qlsv.view;

import com.toedter.calendar.JDateChooser;
import vn.viettuts.qlsv.entity.Order;
import vn.viettuts.qlsv.utils.CalendarUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author Administrator
 */
public class OrderView extends javax.swing.JFrame {
    private final SimpleDateFormat sdf;

    /**
     * Creates new form Exporter
     */
    public OrderView() {
        initComponents();
        sdf = new SimpleDateFormat("dd-MM-yyyy");

        dpOrderDate.setDateFormatString("dd-MM-yyyy");


        txtTotalMoney.setText("0");
        txtTotalMoney.setEditable(false);

        CalendarUtils.disableDateChooserTextEditing(dpOrderDate);
        setLocationRelativeTo(null);

    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbExport = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtTotalMoney = new javax.swing.JTextField();
        dpOrderDate = new com.toedter.calendar.JDateChooser();
        btnAdd = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnRefresh = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnDetail = new javax.swing.JButton();
        btnExport = new javax.swing.JButton();
        btnMin = new javax.swing.JButton();
        btnMax = new javax.swing.JButton();
        btnSearchByID = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Phiếu xuất hàng");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(289, 6, -1, -1));

        tbExport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Mã phiếu xuất", "Ngày xuất", "Tổng tiền"
            }
        ));
        tbExport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbExportMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbExport);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 80, 431, 288));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Mã phiếu xuất:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 77, -1, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Ngày xuất:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 119, -1, -1));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tổng tiền:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 157, -1, -1));
        getContentPane().add(txtId, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 77, 179, -1));
        getContentPane().add(txtTotalMoney, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 157, 179, -1));
        getContentPane().add(dpOrderDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(124, 117, 179, -1));

        btnAdd.setText("Tạo phiếu");
        getContentPane().add(btnAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 273, -1, -1));

        btnUpdate.setText("Sửa phiếu");
        getContentPane().add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 273, -1, -1));

        btnRefresh.setText("Làm mới");
        getContentPane().add(btnRefresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 314, 82, -1));

        btnDelete.setText("Xoá phiếu");
        getContentPane().add(btnDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 314, -1, -1));

        btnDetail.setText("Chi tiết phiếu");
        getContentPane().add(btnDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(651, 42, -1, -1));

        btnExport.setText("Xuất báo cáo");
        getContentPane().add(btnExport, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 349, -1, -1));

        btnMin.setText("Lấy ra đơn nhiều ít tiền nhất");
        getContentPane().add(btnMin, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 180, 40));

        btnMax.setText("Lấy ra đơn nhiều tiền nhất");
        getContentPane().add(btnMax, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 380, 180, 40));

        btnSearchByID.setText("Tìm kiếm");
        getContentPane().add(btnSearchByID, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 80, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/z5536159931127_87ac2b07fee70f1c2fb43b06c741d660.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 470));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbExportMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbExportMouseClicked
        int selectedRow = tbExport.getSelectedRow();
        txtId.setText(tbExport.getValueAt(selectedRow, 0).toString());
        dpOrderDate.setDate(CalendarUtils.convertStringToCalendar(tbExport.getValueAt(selectedRow, 1).toString()).getTime());
        txtTotalMoney.setText(tbExport.getValueAt(selectedRow, 2).toString());
    }//GEN-LAST:event_tbExportMouseClicked

    public void setBtnAddActionListener(java.awt.event.ActionListener actionListener) {
        btnAdd.addActionListener(actionListener);
    }

    public void setBtnUpdateActionListener(java.awt.event.ActionListener actionListener) {
        btnUpdate.addActionListener(actionListener);
    }

    public void setBtnDeleteActionListener(java.awt.event.ActionListener actionListener) {
        btnDelete.addActionListener(actionListener);
    }

    public void setBtnRefreshActionListener(java.awt.event.ActionListener actionListener) {
        btnRefresh.addActionListener(actionListener);
    }
    public void setBtnExportActionListener(java.awt.event.ActionListener actionListener) {
        btnExport.addActionListener(actionListener);
    }

    public void setDetailActionListener(java.awt.event.ActionListener actionListener) {
        btnDetail.addActionListener(actionListener);
    }
    public void setBtnMaxActionListener(java.awt.event.ActionListener actionListener) {
        btnMax.addActionListener(actionListener);
    }
    public void setBtnMinActionListener(java.awt.event.ActionListener actionListener) {
        btnMin.addActionListener(actionListener);
    }

    public void setBtnSearchByIDActionListener(java.awt.event.ActionListener actionListener) {
        btnSearchByID.addActionListener(actionListener);
    }
    public String getIdInput(){
        return txtId.getText().toLowerCase();
    }

    public void clear() {
        txtTotalMoney.setText("0");
//        txtId.setText(String.format("O%03d", id + 1));
        txtId.setText("");
        dpOrderDate.setDate(null);
        tbExport.clearSelection();
    }

//    public void setId(int id) {
//        txtId.setText(String.format("O%03d", id + 1));
//    }

    public Order getOrderInput() {
        String id = txtId.getText();
        String totalMoney = txtTotalMoney.getText();
        String orderDate;

        if (id.isEmpty() || totalMoney.isEmpty()) {
            throw new IllegalArgumentException("Vui lòng nhập đầy đủ thông tin.");
        }

        try {
            orderDate = sdf.format(dpOrderDate.getDate());
        } catch (Exception e) {
            return null;
        }

        Calendar orderDateCal = CalendarUtils.convertStringToCalendar(orderDate);
        return new Order(id, orderDateCal, totalMoney);
    }

    public void setDataToTable(List<Order> orderList) {
        while (tbExport.getRowCount() > 0) {
            ((javax.swing.table.DefaultTableModel) tbExport.getModel()).removeRow(0);
        }

        if(orderList == null || orderList.isEmpty()){
            return;
        }

        String[][] data = new String[orderList.size()][3];
        for (int i = 0; i < orderList.size(); i++) {
            Order order = orderList.get(i);
            data[i][0] = order.getId();
            data[i][1] = order.getOrderDate();
            data[i][2] = String.format("%s VNĐ",order.getTotalMoney());
        }
        tbExport.setModel(new javax.swing.table.DefaultTableModel(
                data,
                new String[]{
                        "Mã phiếu xuất", "Ngày xuất", "Tổng tiền"
                }
        ));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnDetail;
    private javax.swing.JButton btnExport;
    private javax.swing.JButton btnMax;
    private javax.swing.JButton btnMin;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JButton btnSearchByID;
    private javax.swing.JButton btnUpdate;
    private com.toedter.calendar.JDateChooser dpOrderDate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbExport;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtTotalMoney;
    // End of variables declaration//GEN-END:variables
}
