/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.AskListener;
import Controllers.BidListener;
import Controllers.LastListener;
import Models.DataOrderBook;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.RegularTimePeriod;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

/**
 *
 * @author Nicolas_2
 */
public class GraphView implements ActionListener, AskListener, BidListener, LastListener {

    private TimeSeries BidValuePlot;
    private TimeSeries AskValuePlot;
    private TimeSeries ExecutedValuePlot;

    private TimeSeries VolumeBidPlot;
    private TimeSeries VolumeAskPlot;
    private TimeSeries VolumeExecPlot;

    final TimeSeriesCollection dataset2;
    final TimeSeriesCollection dataset3;
    final TimeSeriesCollection dataset4;
    RegularTimePeriod Tdebut;
    private int i = 0; //compteur 
    private int y = 0; //compteur 
    private double lastValueBid;
    private double lastValueAsk;
    private double lastValueExecuted;

    private int lastVolumeBid = 0;
    private int lastVolumeAsk = 0;
    private int lastVolumeExecuted = 0;
    private ChartPanel chartPanel;
    private JFreeChart chart;
    private XYPlot plot;
    private Timer timer ;

    private double newValueBid;
    private double newValueAsk;
    private double lastexectuednew;

    private int newVolumeBid = 0;
    private int newVolumeAsk = 0;
    private int newVolumeExecuted = 0;
    

    private String graphTitle;

    private boolean displayVolumeAsk = false;
    private boolean displayVolumeBid = false;
    private boolean displayVolumeExecuted = false;

    private ValueAxis xaxis2;

    public GraphView(RegularTimePeriod T, String title, double openPrice) {
        graphTitle = title;
        
        lastValueAsk=openPrice;
        lastValueBid=openPrice;
        lastValueExecuted=openPrice;
        
        newValueAsk=openPrice;
        newValueBid=openPrice;
        lastexectuednew=openPrice;
        
        this.BidValuePlot = new TimeSeries("Bid", Millisecond.class);
        this.AskValuePlot = new TimeSeries("Ask", Millisecond.class);
        this.VolumeBidPlot = new TimeSeries("Volume Bid", Millisecond.class);
        this.VolumeAskPlot = new TimeSeries("Volume Ask", Millisecond.class);
        this.ExecutedValuePlot = new TimeSeries("Last Executed", Millisecond.class);
        this.VolumeExecPlot = new TimeSeries("Volume Last Executed", Millisecond.class);

        this.Tdebut = T;
        final TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(BidValuePlot);
        dataset.addSeries(AskValuePlot);
        this.BidValuePlot.add(Tdebut, lastValueBid);
        this.AskValuePlot.add(Tdebut, lastValueAsk);
        dataset2 = new TimeSeriesCollection();
        dataset2.addSeries(VolumeBidPlot);
        dataset2.addSeries(VolumeAskPlot);
        this.VolumeBidPlot.add(Tdebut, lastVolumeBid);
        this.VolumeAskPlot.add(Tdebut, lastVolumeAsk);

        dataset3 = new TimeSeriesCollection();
        dataset3.addSeries(ExecutedValuePlot);
        dataset4 = new TimeSeriesCollection();

        dataset4.addSeries(VolumeExecPlot);
        chart = createChart(dataset);

        //Sets background color of chart
        chart.setBackgroundPaint(Color.LIGHT_GRAY);
        chartPanel = new ChartPanel(chart);
        
        timer = new Timer(0, this);
        timer.start();

    }

    public ChartPanel getChartPanel() {
        return this.chartPanel;
    }
    
    
    public void pause(){
        timer.stop();
    
}
    public void reprendre(){
       timer.start();

    }
    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                graphTitle,
                "Time",
                "Price",
                dataset,
                true,
                true,
                false
        );

        plot = result.getXYPlot();

        plot.setBackgroundPaint(new Color(0xffffe0));
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(Color.lightGray);
        plot.setRangeGridlinesVisible(true);
        plot.setRangeGridlinePaint(Color.lightGray);

        ValueAxis xaxis = plot.getDomainAxis();
        xaxis.setAutoRange(true);
        xaxis.setVisible(false);

        xaxis.setFixedAutoRange(16000.0);  // 60 seconds
        xaxis.setVerticalTickLabels(true);

        ValueAxis yaxis = plot.getRangeAxis();
        yaxis.setRange(0, 100.0);

        xaxis2 = new NumberAxis("Volume");

        plot.setDataset(1, dataset2);

        plot.setRangeAxis(1, xaxis2);
        plot.mapDatasetToRangeAxis(1, 1);

        XYBarRenderer renderer2 = new XYBarRenderer(0.20);
        renderer2.setToolTipGenerator(
                new StandardXYToolTipGenerator(
                        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                        new SimpleDateFormat("d-MMM-yyyy"), new DecimalFormat("0,000.00")
                )
        );
        renderer2.setToolTipGenerator(StandardXYToolTipGenerator.getTimeSeriesInstance());

        plot.setRenderer(1, renderer2);

        plot.setDataset(2, dataset3);

        plot.mapDatasetToRangeAxis(2, 0);

        XYErrorRenderer renderer3 = new XYErrorRenderer();
        plot.setRenderer(2, renderer3);

        plot.setDataset(3, dataset4);
        plot.mapDatasetToRangeAxis(3, 1);
        XYBarRenderer renderer4 = new XYBarRenderer(0.20);

        plot.setRenderer(3, renderer4);

        return result;
    }

    @Override
    public void actionPerformed(final ActionEvent e) {

        for (int i = 0; i < 50; i++) {
            Tdebut = Tdebut.next();
        }

        this.BidValuePlot.add(Tdebut, lastValueBid);
        this.AskValuePlot.add(Tdebut, lastValueAsk);
        this.lastValueBid = newValueBid;
        this.lastValueAsk = newValueAsk;
        this.lastVolumeBid = newVolumeBid;
        this.lastVolumeAsk = newVolumeAsk;

        this.BidValuePlot.add(Tdebut.next(), newValueBid);
        this.AskValuePlot.add(Tdebut.next(), newValueAsk);

        if (displayVolumeAsk || displayVolumeBid || displayVolumeExecuted) {
            xaxis2.setVisible(true);
        }else{
            xaxis2.setVisible(false);
        }

        if (displayVolumeBid) {
            this.VolumeBidPlot.add(Tdebut.next(), newVolumeBid);
        }
        if (displayVolumeAsk) {
            this.VolumeAskPlot.add(Tdebut.next(), newVolumeAsk);
        }

        if (i != y) {
            y = i;
            this.lastVolumeExecuted = newVolumeExecuted;
            this.ExecutedValuePlot.add(Tdebut.next(), lastexectuednew);

            if (displayVolumeExecuted) {
                this.VolumeExecPlot.add(Tdebut.next(), newVolumeExecuted);
            }
        }

    }

    @Override
    public void handleBestBid(DataOrderBook data) {

        newValueBid = data.getPrice();
        newVolumeBid = data.getVolume();

    }

    @Override
    public void handleBestAsk(DataOrderBook data) {
        newValueAsk = data.getPrice();
        newVolumeAsk = data.getVolume();

    }

    @Override
    public void handleBestLast(int time, double price, int volume) {
        lastexectuednew = price;
        newVolumeExecuted = volume;
        //???
        i++;

    }

    public void Setzoomin() {

        chartPanel.zoomInBoth(2, 2);
    }

    public void Setzoomout() {

        chartPanel.zoomOutBoth(2, 2);
    }

    public void reset() {
        chartPanel.restoreAutoBounds();
    }

    public void setDisplayVolumeBid(boolean value) {
        displayVolumeBid = value;
    }

    public void setDisplayVolumeAsk(boolean value) {
        displayVolumeAsk = value;
    }

    public void setDisplayVolumeExecuted(boolean value) {
        displayVolumeExecuted = value;
    }
}
