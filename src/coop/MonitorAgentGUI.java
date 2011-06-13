/*
 * Cooperative Systems
 */

/*
 * MonitorAgentGUI.java
 *
 * Created on Jun 30, 2010, 5:21:09 PM
 */
package src.coop;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mru
 */
public class MonitorAgentGUI extends javax.swing.JFrame {

    private DefaultComboBoxModel plans = new DefaultComboBoxModel();
    private Map<String, List<Resource>> planResources = new HashMap<String, List<Resource>>();

    /** Creates new form MonitorAgentGUI */
    public MonitorAgentGUI() {
        initComponents();
    }

    void addPlan(String plan, jade.util.leap.List leapResources) {

        plans.addElement(plan);
        LinkedList<Resource> resources = new LinkedList<Resource>();
        for ( int i = 0; i < leapResources.size(); i ++) {
            Resource r = (Resource)leapResources.get(i);
            resources.add(r);
        }
        planResources.put(plan, resources);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBoxPlan = new javax.swing.JComboBox();
        jButtonProduce = new javax.swing.JButton();
        jSpinnerCount = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setTitle("Produce New Item");

        jComboBoxPlan.setModel(plans);
        jComboBoxPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxPlanActionPerformed(evt);
            }
        });

        jButtonProduce.setText("Produce");
        jButtonProduce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonProduceActionPerformed(evt);
            }
        });

        jSpinnerCount.setModel(new javax.swing.SpinnerNumberModel(1, 1, 100, 1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Component", "Count"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jComboBoxPlan, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSpinnerCount, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonProduce))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBoxPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinnerCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonProduce))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxPlanActionPerformed

        DefaultTableModel m = (DefaultTableModel) jTable1.getModel();
        while (m.getRowCount() > 0) {
            m.removeRow(0);
        }

        String selected = (String) jComboBoxPlan.getSelectedItem();
        System.out.println(selected + planResources.containsKey(selected));
        if (selected != null && planResources.containsKey(selected)) {
            for (Resource r : planResources.get(selected)) {
                m.addRow(new Object[]{r.getId(), r.getCount()});
            }

        }
    }//GEN-LAST:event_jComboBoxPlanActionPerformed

    private void jButtonProduceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonProduceActionPerformed

        for (ProduceActionListener l : produceActionListeners) {

            l.actionPerformed((String) jComboBoxPlan.getSelectedItem(), (Integer) jSpinnerCount.getValue());
        }
    }//GEN-LAST:event_jButtonProduceActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new MonitorAgentGUI().setVisible(true);


            }
        });


    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonProduce;
    private javax.swing.JComboBox jComboBoxPlan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinnerCount;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    void addProduceListener(ProduceActionListener onProduce) {
        produceActionListeners.add(onProduce);


    }
    private List<ProduceActionListener> produceActionListeners = new LinkedList<ProduceActionListener>();
}