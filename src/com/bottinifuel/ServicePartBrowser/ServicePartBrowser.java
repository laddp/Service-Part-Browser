/*
 * Created on Jan 19, 2007 by pladd
 *
 */
package com.bottinifuel.ServicePartBrowser;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Formatter;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import com.bottinifuel.Energy.Info.InfoFactory;
import com.bottinifuel.Energy.Info.ServicePart;


public class ServicePartBrowser extends JFrame
{
    private static InfoFactory EnergyInfo;
    
    private JPanel PartPanel;
    private JTextField EnergyPartNumber;
    private static final long serialVersionUID = 1L;

    private JLabel hdrPartNum = new JLabel("ServicePart #");
    private GridBagConstraints partConstraints = new GridBagConstraints();

    private JLabel hdrMfg = new JLabel("Manufacturer");
    private GridBagConstraints mfgConstraints = new GridBagConstraints();

    private JLabel hdrGroup = new JLabel("Group");
    private GridBagConstraints groupConstraints = new GridBagConstraints();

    private JLabel hdrStockID = new JLabel("Stock ID");
    private GridBagConstraints stockIDConstraints = new GridBagConstraints();

    private JLabel hdrUnits = new JLabel("Units");
    private GridBagConstraints unitsConstraints = new GridBagConstraints();

    private JLabel hdrDescription = new JLabel("Description");
    private GridBagConstraints descriptionConstraints = new GridBagConstraints();

    private JLabel hdrCost = new JLabel("Cost");
    private GridBagConstraints costConstraints = new GridBagConstraints();

    private JLabel hdrMult = new JLabel("X");
    private GridBagConstraints multConstraints = new GridBagConstraints();

    private JLabel hdrPrice = new JLabel("Price");
    private GridBagConstraints priceConstraints = new GridBagConstraints();

    private JLabel hdrA = new JLabel("A");
    private GridBagConstraints aConstraints = new GridBagConstraints();

    private JLabel hdrB = new JLabel("B");
    private GridBagConstraints bConstraints = new GridBagConstraints();

    private JLabel hdrC = new JLabel("C");
    private GridBagConstraints cConstraints = new GridBagConstraints();

    private JLabel hdrD = new JLabel("D");
    private GridBagConstraints dConstraints = new GridBagConstraints();

    private JLabel hdrE = new JLabel("E");
    private GridBagConstraints eConstraints = new GridBagConstraints();

    private JLabel hdrF = new JLabel("F");
    private GridBagConstraints fConstraints = new GridBagConstraints();

    private JLabel hdrG = new JLabel("G");
    private GridBagConstraints gConstraints = new GridBagConstraints();

    
    public ServicePartBrowser()
    {
        super("Bottini Service ServicePart Browser");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(750, 375);

        String energyHost = null;
        int    energyPort = 0;
        String energyDB = null;
        String energyLogin = null;
        String energyPassword = null;
        try {
            ClassLoader cl = this.getClass().getClassLoader();
            URL mfURL = cl.getResource("EnergyLoginInfo.manifest");
            Manifest manifest = new Manifest(mfURL.openStream());
            Attributes attr = manifest.getMainAttributes();
            energyHost     = attr.getValue("EnergyHost");
            energyPort     = Integer.valueOf(attr.getValue("EnergyPort"));
            energyDB       = attr.getValue("EnergyDB");
            energyLogin    = attr.getValue("EnergyUser");
            energyPassword = attr.getValue("EnergyPW");
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog(null,
                                          "Unable to retrieve Energy Login information\n" + e.getCause().toString(),
                                          "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            if (EnergyInfo == null)
                EnergyInfo = new InfoFactory(energyHost, energyPort, energyDB, energyLogin, energyPassword);
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Unable to open Energy database connection\n" + e.getCause().toString(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Border lineBorder = BorderFactory.createLineBorder(Color.black);
        hdrPartNum.setBorder(lineBorder);
        hdrDescription.setBorder(lineBorder);
        hdrMfg.setBorder(lineBorder);
        hdrGroup.setBorder(lineBorder);
        hdrStockID.setBorder(lineBorder);
        hdrUnits.setBorder(lineBorder);
        hdrCost.setBorder(lineBorder);
        hdrMult.setBorder(lineBorder);
        hdrPrice.setBorder(lineBorder);
        hdrA.setBorder(lineBorder);
        hdrB.setBorder(lineBorder);
        hdrC.setBorder(lineBorder);
        hdrD.setBorder(lineBorder);
        hdrE.setBorder(lineBorder);
        hdrF.setBorder(lineBorder);
        hdrG.setBorder(lineBorder);
        
        int colNum = 0;
        partConstraints.anchor = GridBagConstraints.NORTHWEST;
        partConstraints.gridx = colNum++;
        partConstraints.gridy = -1;
        partConstraints.insets = new Insets(0, 0, 0, 5);

        descriptionConstraints.anchor = GridBagConstraints.NORTHWEST;
        descriptionConstraints.gridx = colNum++;
        descriptionConstraints.gridy = -1;
        descriptionConstraints.insets = new Insets(0, 0, 0, 5);

        mfgConstraints.anchor = GridBagConstraints.NORTHWEST;
        mfgConstraints.gridx = colNum++;
        mfgConstraints.gridy = -1;
        mfgConstraints.insets = new Insets(0, 0, 0, 5);

        groupConstraints.anchor = GridBagConstraints.NORTHWEST;
        groupConstraints.gridx = colNum++;
        groupConstraints.gridy = -1;
        groupConstraints.insets = new Insets(0, 0, 0, 5);

        stockIDConstraints.anchor = GridBagConstraints.NORTHWEST;
        stockIDConstraints.gridx = colNum++;
        stockIDConstraints.gridy = -1;
        stockIDConstraints.insets = new Insets(0, 0, 0, 5);

        unitsConstraints.anchor = GridBagConstraints.NORTHWEST;
        unitsConstraints.gridx = colNum++;
        unitsConstraints.gridy = -1;
        unitsConstraints.insets = new Insets(0, 0, 0, 5);

        costConstraints.anchor = GridBagConstraints.NORTHEAST;
        costConstraints.gridx = colNum++;
        costConstraints.gridy = -1;
        costConstraints.insets = new Insets(0, 0, 0, 5);

        multConstraints.anchor = GridBagConstraints.NORTHEAST;
        multConstraints.gridx = colNum++;
        multConstraints.gridy = -1;
        multConstraints.insets = new Insets(0, 0, 0, 5);

        priceConstraints.anchor = GridBagConstraints.NORTHEAST;
        priceConstraints.gridx = colNum++;
        priceConstraints.gridy = -1;
        priceConstraints.insets = new Insets(0, 0, 0, 5);

        aConstraints.anchor = GridBagConstraints.NORTHWEST;
        aConstraints.gridx = colNum++;
        aConstraints.gridy = -1;
        aConstraints.insets = new Insets(0, 0, 0, 2);

        bConstraints.anchor = GridBagConstraints.NORTHWEST;
        bConstraints.gridx = colNum++;
        bConstraints.gridy = -1;
        bConstraints.insets = new Insets(0, 0, 0, 2);

        cConstraints.anchor = GridBagConstraints.NORTHWEST;
        cConstraints.gridx = colNum++;
        cConstraints.gridy = -1;
        cConstraints.insets = new Insets(0, 0, 0, 2);

        dConstraints.anchor = GridBagConstraints.NORTHWEST;
        dConstraints.gridx = colNum++;
        dConstraints.gridy = -1;
        dConstraints.insets = new Insets(0, 0, 0, 2);

        eConstraints.anchor = GridBagConstraints.NORTHWEST;
        eConstraints.gridx = colNum++;
        eConstraints.gridy = -1;
        eConstraints.insets = new Insets(0, 0, 0, 2);

        fConstraints.anchor = GridBagConstraints.NORTHWEST;
        fConstraints.gridx = colNum++;
        fConstraints.gridy = -1;
        fConstraints.insets = new Insets(0, 0, 0, 2);

        gConstraints.anchor = GridBagConstraints.NORTHWEST;
        gConstraints.gridx = colNum++;
        gConstraints.gridy = -1;
        gConstraints.insets = new Insets(0, 0, 0, 2);

        final JPanel LookupPanel = new JPanel();
        LookupPanel.setLayout(new BoxLayout(LookupPanel, BoxLayout.X_AXIS));
        getContentPane().add(LookupPanel, BorderLayout.NORTH);

        final JLabel energyLabel = new JLabel();
        energyLabel.setText("ServicePart #:");
        energyLabel.setDisplayedMnemonic('P');
        LookupPanel.add(energyLabel);

        NumberFormat nf = NumberFormat.getIntegerInstance();
        nf.setGroupingUsed(false);
        nf.setMaximumFractionDigits(0);
        EnergyPartNumber = new JFormattedTextField(nf);
        EnergyPartNumber.setColumns(5);
        LookupPanel.add(EnergyPartNumber);
        energyLabel.setLabelFor(EnergyPartNumber);

        final JButton LookupBtn = new JButton("Lookup");
        LookupBtn.setMnemonic('L');
        LookupBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                AddPart();
            }
        });
        LookupPanel.add(LookupBtn);

        EnergyPartNumber.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                LookupBtn.doClick();
            }
        });

        final JButton ResetBtn = new JButton("Reset");
        ResetBtn.setMnemonic('R');
        ResetBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                Reset();
            }
        });
        LookupPanel.add(ResetBtn);

        final JScrollPane PartPane = new JScrollPane();
        getContentPane().add(PartPane);

        PartPanel = new JPanel();
        PartPanel.setLayout(new GridBagLayout());
        PartPane.setViewportView(PartPanel);
        Reset();
    }

    private void AddPartToPanel(JPanel panel, ServicePart p)
    {
        JLabel partNum = new JLabel(Integer.toString(p.PartNum));
        panel.add(partNum, partConstraints);
        
        JLabel description = new JLabel(p.getDescription());
        panel.add(description, descriptionConstraints);

        JLabel mfg = new JLabel(p.getManufacturer());
        panel.add(mfg, mfgConstraints);
        
        JLabel group = new JLabel(p.getGroup());
        panel.add(group, groupConstraints);

        JLabel stockID = new JLabel(p.getStockID());
        panel.add(stockID, stockIDConstraints);

        JLabel units = new JLabel(p.Unit_name);
        panel.add(units, unitsConstraints);

        Formatter fmt = new Formatter();

        fmt.format("$%8.2f", p.Cost);
        JLabel cost = new JLabel(fmt.toString());
        panel.add(cost, costConstraints);
        fmt = new Formatter();

        fmt.format("%4.2f", p.Multiplier);
        String multS = fmt.toString(); 
        if (p.OverrideMultiplier)
            multS = "*" + multS;
        JLabel mult = new JLabel(multS);
        panel.add(mult, multConstraints);
        fmt = new Formatter();

        fmt.format("$%8.2f", p.CustCost);
        JLabel price = new JLabel(fmt.toString());
        panel.add(price, priceConstraints);
        fmt = new Formatter();

        JLabel a = new JLabel(p.Warranty_coverage[0]?"Y":"N");
        panel.add(a, aConstraints);

        JLabel b = new JLabel(p.Warranty_coverage[1]?"Y":"N");
        panel.add(b, bConstraints);

        JLabel c = new JLabel(p.Warranty_coverage[2]?"Y":"N");
        panel.add(c, cConstraints);

        JLabel d = new JLabel(p.Warranty_coverage[3]?"Y":"N");
        panel.add(d, dConstraints);

        JLabel e = new JLabel(p.Warranty_coverage[4]?"Y":"N");
        panel.add(e, eConstraints);

        JLabel f = new JLabel(p.Warranty_coverage[5]?"Y":"N");
        panel.add(f, fConstraints);

        JLabel g = new JLabel(p.Warranty_coverage[6]?"Y":"N");
        panel.add(g, gConstraints);
}

    private void ErrorMsgPart(String partNum, String message)
    {
        JLabel pn = new JLabel(partNum);
        PartPanel.add(pn, partConstraints);
        
        JLabel m = new JLabel(message);
        PartPanel.add(m, descriptionConstraints);

        JLabel blank;
        blank = new JLabel("");
        PartPanel.add(blank,         mfgConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,       groupConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,     stockIDConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,       unitsConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,        costConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,        multConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,       priceConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,           aConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,           bConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,           cConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,           dConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,           eConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,           fConstraints);
        blank = new JLabel("");
        PartPanel.add(blank,           gConstraints);

        PartPanel.validate();
        PartPanel.repaint();
    }

    private void Reset()
    {
        PartPanel.removeAll();
        PartPanel.add(hdrPartNum,     partConstraints);
        PartPanel.add(hdrDescription, descriptionConstraints);
        PartPanel.add(hdrMfg,         mfgConstraints);
        PartPanel.add(hdrGroup,       groupConstraints);
        PartPanel.add(hdrStockID,     stockIDConstraints);
        PartPanel.add(hdrUnits,       unitsConstraints);
        PartPanel.add(hdrCost,        costConstraints);
        PartPanel.add(hdrMult,        multConstraints);
        PartPanel.add(hdrPrice,       priceConstraints);
        PartPanel.add(hdrA,           aConstraints);
        PartPanel.add(hdrB,           bConstraints);
        PartPanel.add(hdrC,           cConstraints);
        PartPanel.add(hdrD,           dConstraints);
        PartPanel.add(hdrE,           eConstraints);
        PartPanel.add(hdrF,           fConstraints);
        PartPanel.add(hdrG,           gConstraints);
        EnergyPartNumber.setText("");
        EnergyPartNumber.requestFocus();
        PartPanel.validate();
        PartPanel.repaint();
    }

    private void AddPart()
    {
        try
        {
            String pn = EnergyPartNumber.getText();
            pn = pn.trim();
            if (pn.length() > 0)
            {
                ServicePart p = EnergyInfo.GetServicePartInfo(Integer.valueOf(pn));
                AddPartToPanel(PartPanel, p);
            }
            EnergyPartNumber.setText("");
        }
        catch (Exception e)
        {
            ErrorMsgPart(EnergyPartNumber.getText(), e.getMessage());
        }
        EnergyPartNumber.setText("");
        EnergyPartNumber.requestFocus();
        PartPanel.validate();
        PartPanel.repaint();
    }
    
    
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        ServicePartBrowser spb = new ServicePartBrowser();
        spb.setVisible(true);
    }

}
