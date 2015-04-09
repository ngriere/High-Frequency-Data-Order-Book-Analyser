/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import static Controllers.ClockController.transfoInverse;
import Controllers.ClockListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.ui.ApplicationFrame;


/**
 *
 * @author Nicolas_2
 */
public class GUI_PPE_HFT extends ApplicationFrame implements ClockListener {

    private final JPanel PanelAsk;
    private final JPanel PanelBid;
    private final TabLastExecutedView PanelLastExecuted;
    private final GraphView graphView;
     private JCheckBoxMenuItem VB;
    private JCheckBoxMenuItem VA;
    private JCheckBoxMenuItem VE;
    private JMenuItem SE_BA;
    private JMenuItem EE_BA;
    private JMenuItem SE_EXE;
    private JMenuItem EE_EXE;
    private JMenuItem spread;
    private JMenu BA;
    private JMenu EXE;
    
    

    public GUI_PPE_HFT(final String title, RegularTimePeriod T, double openPrice){

        super("HFT Order Book Analyzer");
        initComponents();
        ImageIcon iconOpen = new ImageIcon("open.png");
        ImageIcon iconExit = new ImageIcon("exit.png");
        JMenuItem exitMi = new JMenuItem("Exit", iconExit);
        exitMi.setToolTipText("Exit application");
        this.VB= new JCheckBoxMenuItem("Volume Bid");
        this.VA= new JCheckBoxMenuItem("Volume Ask");
        this.VE= new JCheckBoxMenuItem("Volume Executed");
        this.SE_BA = new JMenuItem("Start Export Bid/Ask");
        this.EE_BA = new JMenuItem("End Export Bid/Ask");
        this.SE_EXE = new JMenuItem("Start Export Executed");
        this.EE_EXE = new JMenuItem("End Export Executed");
        this.EXE=new JMenu("Executed");
        this.BA=new JMenu("Bid Ask");


        exitMi.addActionListener(new ActionListener() {
            

            @Override
            public void actionPerformed(ActionEvent ae) {
            System.exit(0);            }
        });
        
        
        
        jMenu1.add(exitMi);
        jMenu3.add(VB);
        jMenu3.add(VA);
        jMenu3.add(VE);
        jMenu4.add(BA);
        jMenu4.add(EXE);
        BA.add(SE_BA);
        BA.add(EE_BA);
        EXE.add(SE_EXE);
        EXE.add(EE_EXE);
     

        // Construct Graph
        graphView = new GraphView(T, title, openPrice);
        constructGraph(graphView);
        
        // Construct table Ask Orders
        PanelAsk = new TabAskView();
        SplitPaneOrderBook.setRightComponent(PanelAsk);

        // Construct table Bid Orders
        PanelBid = new TabBidView();
        SplitPaneOrderBook.setLeftComponent(PanelBid);

        // Construct table Last Executed Orders
        PanelLastExecuted = new TabLastExecutedView();
        SplitPaneLastExecuted.setBottomComponent(PanelLastExecuted);
    }

    
        public JMenuItem getSE_BA() {
        return SE_BA;
    }

    public JMenuItem getEE_BA() {
        return EE_BA;
    }
    public JMenuItem getSE_EXE() {
        return SE_EXE;
    }

    public JMenuItem getEE_EXE() {
        return EE_EXE;
    }
    public JMenuItem getSpread() {
        return spread;
    }
    
 

    
    public JTextPane getjHigh() {
        return jHigh;
    }

    public JTextPane getjLow() {
        return jLow;
    }

    public JTextPane getjOpen() {
        return jOpen;
    }

    public JTextPane getjVariation() {
        return jVariation;
    }

    public JTextPane getjVolatility() {
        return jVolatility;
    }

    public JTextPane getjVolume() {
        return jVolume;
    }

    public JPanel getjPanelFunctions() {
        return jPanelFunctions;
    }

    public JCheckBoxMenuItem getVB() {
        return VB;
    }

    public JCheckBoxMenuItem getVA() {
        return VA;
    }
    
     public JCheckBoxMenuItem getVE() {
        return VE;
    }

    public TabAskView getPanelAsk() {
        return (TabAskView) PanelAsk;
    }

    public TabBidView getPanelBid() {
        return (TabBidView) PanelBid;
    }

    public TabLastExecutedView getPanelLastExecuted() {
        return (TabLastExecutedView) PanelLastExecuted;
    }

    private void constructGraph(GraphView graphView) {
         PanelGraph.add(graphView.getChartPanel());
    }
    
    public GraphView getGraphView() {
        return (GraphView) graphView;
    }
    
    public JButton getButtonPause(){
        return ButtonPause;
    }
    
    public JButton getButtonResume(){
        return ButtonResume;
    }
    
    public JTextPane getLabelClock(){
        return LabelClock;
    }
    
  
    
    
      @Override
    public void handleClock(int time) {
        this.LabelClock.setText(""+transfoInverse(time));
        this.LabelClock2.setText(""+transfoInverse(time-4500));
        this.LabelClock1.setText(""+transfoInverse(time-9000));
        
        this.jPanel1.repaint();
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        PanelGraph = new javax.swing.JPanel();
        jButton4 = new javax.swing.JButton();
        ButtonReset = new javax.swing.JButton();
        ButtonPause = new javax.swing.JButton();
        ButtonResume = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        LabelClock = new javax.swing.JTextPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        LabelClock1 = new javax.swing.JTextPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        LabelClock2 = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jSplitPane3 = new javax.swing.JSplitPane();
        jPanel5 = new javax.swing.JPanel();
        jSplitPane4 = new javax.swing.JSplitPane();
        jPanel7 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        LabelOrderBook = new javax.swing.JLabel();
        SplitPaneOrderBook = new javax.swing.JSplitPane();
        SplitPaneLastExecuted = new javax.swing.JSplitPane();
        jLabel1 = new javax.swing.JLabel();
        jPanelFunctions = new javax.swing.JPanel();
        jSeparator3 = new javax.swing.JSeparator();
        LabelToday = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        LabelOpen = new javax.swing.JLabel();
        LabelLow = new javax.swing.JLabel();
        LabelHigh = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jOpen = new javax.swing.JTextPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jLow = new javax.swing.JTextPane();
        jScrollPane6 = new javax.swing.JScrollPane();
        jHigh = new javax.swing.JTextPane();
        jSeparator7 = new javax.swing.JSeparator();
        LabelVariation = new javax.swing.JLabel();
        LabelVolume = new javax.swing.JLabel();
        LabelVolatility = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jVariation = new javax.swing.JTextPane();
        jScrollPane9 = new javax.swing.JScrollPane();
        jVolume = new javax.swing.JTextPane();
        jScrollPane10 = new javax.swing.JScrollPane();
        jVolatility = new javax.swing.JTextPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jSplitPane1.setDividerLocation(700);

        jPanel1.setPreferredSize(new java.awt.Dimension(700, 600));

        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        PanelGraph.setLayout(new java.awt.BorderLayout());

        jButton4.setText("-");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        ButtonReset.setText("Reset");
        ButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonResetActionPerformed(evt);
            }
        });

        ButtonPause.setText("Pause");
        ButtonPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ButtonPauseActionPerformed(evt);
            }
        });

        ButtonResume.setText("Resume");

        LabelClock.setEditable(false);
        jScrollPane1.setViewportView(LabelClock);

        LabelClock1.setEditable(false);
        jScrollPane2.setViewportView(LabelClock1);

        LabelClock2.setEditable(false);
        jScrollPane3.setViewportView(LabelClock2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(ButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(ButtonPause)
                        .addGap(18, 18, 18)
                        .addComponent(ButtonResume)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 214, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(PanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(PanelGraph, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ButtonPause)
                            .addComponent(jButton3)
                            .addComponent(ButtonResume)
                            .addComponent(ButtonReset)
                            .addComponent(jButton4))
                        .addGap(124, 124, 124))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(165, 165, 165))))
        );

        jSplitPane1.setLeftComponent(jPanel1);

        jPanel2.setPreferredSize(new java.awt.Dimension(300, 600));

        jSplitPane3.setDividerLocation(400);
        jSplitPane3.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jSplitPane4.setDividerLocation(200);
        jSplitPane4.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        LabelOrderBook.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelOrderBook.setForeground(new java.awt.Color(0, 102, 204));
        LabelOrderBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelOrderBook.setText("Order Book");

        SplitPaneOrderBook.setDividerLocation(218);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(LabelOrderBook, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
            .addComponent(SplitPaneOrderBook)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(LabelOrderBook, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SplitPaneOrderBook))
        );

        jSplitPane4.setTopComponent(jPanel7);

        SplitPaneLastExecuted.setDividerLocation(25);
        SplitPaneLastExecuted.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 204));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Last Executed Orders");
        SplitPaneLastExecuted.setTopComponent(jLabel1);

        jSplitPane4.setRightComponent(SplitPaneLastExecuted);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane4, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jSplitPane3.setTopComponent(jPanel5);

        LabelToday.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelToday.setForeground(new java.awt.Color(0, 102, 204));
        LabelToday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        LabelToday.setText("Today");

        LabelOpen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelOpen.setForeground(new java.awt.Color(0, 102, 204));
        LabelOpen.setText("Open");

        LabelLow.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelLow.setForeground(new java.awt.Color(0, 102, 204));
        LabelLow.setText("Low");

        LabelHigh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelHigh.setForeground(new java.awt.Color(0, 102, 204));
        LabelHigh.setText("High");

        jScrollPane4.setViewportView(jOpen);

        jScrollPane5.setViewportView(jLow);

        jScrollPane6.setViewportView(jHigh);

        LabelVariation.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelVariation.setForeground(new java.awt.Color(0, 102, 204));
        LabelVariation.setText("Variation");

        LabelVolume.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelVolume.setForeground(new java.awt.Color(0, 102, 204));
        LabelVolume.setText("Volume");

        LabelVolatility.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LabelVolatility.setForeground(new java.awt.Color(0, 102, 204));
        LabelVolatility.setText("Volatility");

        jScrollPane8.setViewportView(jVariation);

        jScrollPane9.setViewportView(jVolume);

        jScrollPane10.setViewportView(jVolatility);

        javax.swing.GroupLayout jPanelFunctionsLayout = new javax.swing.GroupLayout(jPanelFunctions);
        jPanelFunctions.setLayout(jPanelFunctionsLayout);
        jPanelFunctionsLayout.setHorizontalGroup(
            jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addComponent(LabelToday, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator4)
            .addComponent(jSeparator5)
            .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                        .addComponent(LabelOpen)
                        .addGap(66, 66, 66)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LabelVariation)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(LabelLow)
                        .addGap(72, 72, 72)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LabelVolume)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                        .addComponent(LabelHigh)
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LabelVolatility)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))))
            .addComponent(jSeparator7)
        );
        jPanelFunctionsLayout.setVerticalGroup(
            jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                .addComponent(LabelToday, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(LabelOpen, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFunctionsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelVariation, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(44, 44, 44)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(LabelLow, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFunctionsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(LabelVolume, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(44, 44, 44)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelFunctionsLayout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(LabelHigh, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelFunctionsLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelFunctionsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LabelVolatility, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jSplitPane3.setRightComponent(jPanelFunctions);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)
        );

        jSplitPane1.setRightComponent(jPanel2);

        jMenu1.setText("File");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu3.setText("Tools");
        jMenuBar1.add(jMenu3);

        jMenu4.setText("Export");
        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1148, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 751, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ButtonPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonPauseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ButtonPauseActionPerformed

    private void ButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ButtonResetActionPerformed
       
         graphView.reset();
    }//GEN-LAST:event_ButtonResetActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        graphView.Setzoomout();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
         graphView.Setzoomin();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonPause;
    private javax.swing.JButton ButtonReset;
    private javax.swing.JButton ButtonResume;
    private javax.swing.JTextPane LabelClock;
    private javax.swing.JTextPane LabelClock1;
    private javax.swing.JTextPane LabelClock2;
    private javax.swing.JLabel LabelHigh;
    private javax.swing.JLabel LabelLow;
    private javax.swing.JLabel LabelOpen;
    private javax.swing.JLabel LabelOrderBook;
    private javax.swing.JLabel LabelToday;
    private javax.swing.JLabel LabelVariation;
    private javax.swing.JLabel LabelVolatility;
    private javax.swing.JLabel LabelVolume;
    private javax.swing.JPanel PanelGraph;
    private javax.swing.JSplitPane SplitPaneLastExecuted;
    private javax.swing.JSplitPane SplitPaneOrderBook;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JTextPane jHigh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextPane jLow;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JTextPane jOpen;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanelFunctions;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane3;
    private javax.swing.JSplitPane jSplitPane4;
    private javax.swing.JTextPane jVariation;
    private javax.swing.JTextPane jVolatility;
    private javax.swing.JTextPane jVolume;
    // End of variables declaration//GEN-END:variables

}
