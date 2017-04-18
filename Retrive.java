
import com.atul.JavaOpenCV.Imshow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Sathvik
 */
public class Retrive extends JFrame implements ActionListener,ChangeListener{
	 		public JTextField distance_value = new JTextField(10);
	 		public JButton fetch = new JButton("Load By Distance");
	 		int value_scroll;
	 		public Double[] values_set = new Double[15];
	 		public String[] path_set = new String[15];
    
            public static void main(String args[])
            {
                Retrive r = new Retrive();
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                Frame f = new Frame();
                FileDialog fd = new FileDialog(f,"Enter query image",FileDialog.LOAD);
                fd.setVisible(true);
                String path = fd.getDirectory().concat(fd.getFile());
                r.reterives(path,0);
                /*Frame f = new Frame();
                FileDialog fd = new FileDialog(f,"input directory",FileDialog.LOAD);
                fd.setVisible(true);   
                File directory = new File(fd.getDirectory());
                File[] list = directory.listFiles();
                int len = directory.listFiles().length;
                Mat[] img_corpse = new Mat[len];
                Mat[] histo = new Mat[len];
                for(int i = 0;i < len; i++)
                {
                  img_corpse[i] = Highgui.imread(list[i].toString());
                  Imgproc.cvtColor(img_corpse[i],img_corpse[i], Imgproc.COLOR_RGB2GRAY);
                  System.out.println(list[i]);
                  //System.out.println(img_corpse[i].dump());
                  histo[i]=r.histo(img_corpse[i]);
                }
                
                Mat query = Highgui.imread(r.query_read());
                Imgproc.cvtColor(query,query, Imgproc.COLOR_RGB2GRAY);
                Double[] distance = new Double[len];
                Mat histquery = new Mat();
				histquery = r.query_histo(query);
                for(int i = 0;i < len; i++)
                {
                    r.RGBtoGRAY(query,img_corpse[i]);
                    r.Preprocess(query,img_corpse[i]);
                    System.out.println("size of query"+query.width()+query.height());
                    System.out.println("size of datacorpus"+img_corpse[i].width()+img_corpse[i].height());
                    //Imshow im = new Imshow("title");
                    //im.showImage(img_corpse[i]);
                    distance[i] = r.Find_dist(histquery,histo[i]);
                }
                for(int i = 0;i < len; i++)
                {
                    System.out.println("distance of "+i+" "+distance[i]);
                }
                r.map(list,distance,len);*/
                
                 
                
            }
            public void reterives(String path,int n)
            {
                Retrive r = new Retrive();
                Test1 ts = new Test1();
                System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
                Frame f = new Frame();
                FileDialog fd = new FileDialog(f,"input directory",FileDialog.LOAD);
                fd.setVisible(true);   
                File directory = new File(fd.getDirectory());
                File[] list = directory.listFiles();
                int len = directory.listFiles().length;
                Mat[] img_corpse = new Mat[len];
                Mat[] histo = new Mat[len];
                for(int i = 0;i < len; i++)
                {
                  img_corpse[i] = Highgui.imread(list[i].toString());
                  //Imgproc.cvtColor(img_corpse[i],img_corpse[i], Imgproc.COLOR_RGB2GRAY);
                  System.out.println(list[i]);
                  //System.out.println(img_corpse[i].dump());
                  histo[i]=r.histo(img_corpse[i]);
                }
                distanceofn nd = new distanceofn();
                Mat query = Highgui.imread(path);
                Imgproc.cvtColor(query,query, Imgproc.COLOR_RGB2GRAY);
                Double[] distance = new Double[len];
                Mat histquery = new Mat();
				histquery = r.query_histo(query);
                for(int i = 0;i < len; i++)
                {
                    r.RGBtoGRAY(query,img_corpse[i]);
                    r.Preprocess(query,img_corpse[i]);
                    System.out.println("size of query"+query.width()+query.height());
                    System.out.println("size of datacorpus"+img_corpse[i].width()+img_corpse[i].height());
                    //Imshow im = new Imshow("title");
                    //im.showImage(img_corpse[i]);
                    distance[i] = r.Find_dist(histquery,histo[i]);
                    //distance[i]=nd.ndistance(histquery, histo[i], 2);
                }
                for(int i = 0;i < len; i++)
                {
                    System.out.println("distance of "+i+" "+distance[i]);
                }
                r.map(list,distance,len);
            }
            public String query_read()
            {
                Frame f = new Frame();
                FileDialog fd = new FileDialog(f,"input query image",FileDialog.LOAD);
                fd.setVisible(true);
                String path = fd.getDirectory().concat(fd.getFile());
                return path;
            }
            
            public Double Find_dist(Mat query,Mat img_corpse)
            {
                Double dist = Core.norm( img_corpse,query, Core.NORM_L2);
                return dist;
            }
            
            public void Preprocess(Mat query,Mat img_corpse)
            {
                Imgproc.resize(img_corpse, img_corpse, new Size(400,400));
                
                
               
                Imgproc.resize(query, query, new Size(400,400));
                
               
            }
            
            public void RGBtoGRAY(Mat query,Mat img_cropse)
            {
                Imgproc.cvtColor(img_cropse, img_cropse, Imgproc.COLOR_RGB2GRAY);
                
            }
            public  Mat query_histo(Mat query_img)
			{
				Vector<Mat> bgr_planes = new Vector<>();
	            Core.split(query_img, bgr_planes);
	            
	            MatOfInt histSize = new MatOfInt(256);
	            final MatOfFloat histRange = new MatOfFloat(0f, 256f);
	            //boolean accumulate = false;
	            Mat q_hist = new  Mat();
	            int hist_w = 512;
	            int hist_h = 600;
	            long bin_w;
	            bin_w = Math.round((double) (hist_w / 256));

	            Mat histImage = new Mat(hist_h, hist_w,CvType.CV_8UC3);
	            
	            Imgproc.calcHist(bgr_planes, new MatOfInt(0),new Mat(), q_hist, histSize, histRange);
	            Core.normalize(q_hist, q_hist, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
	            return q_hist;
			}
             public Mat histo(Mat imgs)
			{
				Vector<Mat> bgr_planes = new Vector<>();
	            Core.split(imgs, bgr_planes);
	            
	            MatOfInt histSize = new MatOfInt(256);
	            final MatOfFloat histRange = new MatOfFloat(0f, 256f);
	            boolean accumulate = false;
	            Mat b_hist = new  Mat();
	            int hist_w = 512;
	            int hist_h = 600;
	            //long bin_w;
	            //bin_w = Math.round((double) (hist_w / 256));

	            Mat histImage = new Mat(hist_h, hist_w,CvType.CV_8UC3);
	            
	            Imgproc.calcHist(bgr_planes, new MatOfInt(0),new Mat(), b_hist, histSize, histRange, accumulate);
	            Core.normalize(b_hist, b_hist, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
	            return b_hist;
			}
             public void map(File[] list,Double[] distance,int len)
             {
            	 HashMap<String, Double> map = new HashMap<String, Double>();
            	 for(int i = 0 ; i < len ; i++)
            	 {
            		 map.put(list[i].toString(), distance[i]);
            	 }
                 //System.out.println(map.values());
            	 Hashmap hm = new Hashmap();
            	 Map<String, Double> maps = hm.sortByValues(map);
            	 
            	 System.out.println(maps.keySet());
            	 Set<String> paths = maps.keySet();
            	 Collection<Double> values = maps.values();
            	 String[] array = paths.toArray(new String[paths.size()]);
            	 Double[] array_values = values.toArray(new Double[values.size()]);
            	 
            	 for(int i = 0;i < 15;i++)
            	 {
            		 System.out.println("paths="+array[i]);
            		 System.out.println("values="+array_values[i]);
            		// display(array[i]);
            	 }
                 display(array,array_values);
            	
             }
             public void display(String path[],Double[] values)
             {
            	 int valueminor = 0;
		           int 	valuemajor =0;
            	 //Mat res = Highgui.imread(path);
            	 //Imgproc.resize(res, res, new Size(400,400));
            	 //Imshow im = new Imshow("results");
            	 //im.showImage(res);
            	 JPanel  panel= new JPanel ();
            	 panel.setLayout(new GridLayout(0, 3, 5, 5));
            	 for(int i = 0; i < 15 ; i++)
            	 {
            		 values_set[i] = values[i];
            		 path_set[i] = path[i];
            	 }
            	 
           	  	 final JFrame  frame = new JFrame ("Display multiple images from files.");
           	  	 JPanel newpanel = new JPanel();
           	  	 newpanel.setLayout(new BorderLayout());
           	  	 
           	  	 JLabel sliderLabel = new JLabel("Distance Value", JLabel.CENTER);
           	  	 sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
           	  	
		           	  JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, values[15].intValue(), values[15].intValue());  
		           	  
		           	  if(values[15] >= 100 && values[15] <=500 )
		           	  {
		           		  valueminor = 500;
		           		  valuemajor = 100;
		           	  }
		           	  else if(values[15] >= 50 && values[15] <=250 )
		           	  {
		           		valueminor = 250;
		           		  valuemajor = 50;
		           	  }
		           	  else if(values[15] >= 20 && values[15] <= 100)
		           	  {
		           		valueminor = 100;
		           		  valuemajor = 20;
		           	  }
		           	 else if(values[15] >= 5 && values[15] <= 20)
		           	  {
		           		valueminor = 20;
		           		  valuemajor = 2;
		           	  }
		           	slider.setMajorTickSpacing(valueminor);  
		           	slider.setMajorTickSpacing(valuemajor);  
		           	slider.setPaintTicks(true);  
		           	slider.setPaintLabels(true);  	
		           	slider.addChangeListener(this);
		           	fetch.addActionListener(this);
		           
		           	panel.add(sliderLabel,BorderLayout.NORTH);
		           	panel.add(slider,BorderLayout.NORTH);
		           	panel.add(distance_value,BorderLayout.NORTH);
		           	panel.add(fetch,BorderLayout.NORTH);
           	  	 for(int i = 0 ; i < 15 ; i++)
           	  	 {
           	  		// panel.add (new JLabel (new ImageIcon (path[i])));
           	  		 ImageIcon image = new ImageIcon(path[i]);
           	  		 JLabel label = new JLabel(values[i].toString(), image, JLabel.CENTER);
           	  		 panel.add(label);
           	  		 
           	  	 }
           	  	 	  //set(values);
           	  	 	  JScrollPane scroll = new JScrollPane(panel);
           	  	 	  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
           	  	 	  frame.getContentPane().add(scroll);
           	  	 	  //frame.add(panel);
           	  	 	  //frame.getContentPane().add(scroll);
           	  	 	 
		              frame.pack();
		              
		              frame.setSize(1000,1000);
		              
		              
		              frame.setVisible(true);
		              
		              
		              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		              
             }
             public void display2(String path[],Double[] values,int count)
             {
            	 int valueminor = 0;
		           int 	valuemajor =0;
            	 //Mat res = Highgui.imread(path);
            	 //Imgproc.resize(res, res, new Size(400,400));
            	 //Imshow im = new Imshow("results");
            	 //im.showImage(res);
		          
            	 JPanel  panel= new JPanel ();   
            	 panel.setLayout(new GridLayout(0, 3, 5, 5));
           	  	 final JFrame  frame = new JFrame ("Display multiple images from files.");
           	  	 for(int i = 0 ; i < count ; i++)
           	  	 {
           	  	 ImageIcon image = new ImageIcon(path[i]);
       	  		 JLabel label = new JLabel(values[i].toString(), image, JLabel.CENTER);
       	  		 panel.add(label);
           	  	 }
           	  	 	  //set(values);
           	  	 	  JScrollPane scroll = new JScrollPane(panel);
           	  	 	  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
           	  	 	  frame.getContentPane().add(scroll);
           	  	 	  //frame.add(panel);
           	  	 	  //frame.getContentPane().add(scroll);
           	  	 	 
		              frame.pack();
		              
		              frame.setSize(1000,1000);
		              
		              
		              frame.setVisible(true);
		              
		              
		              frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		              
             }
			@Override
			public void stateChanged(ChangeEvent e) {
				// TODO Auto-generated method stub
				JSlider source = (JSlider)e.getSource();
				value_scroll = source.getValue();
				distance_value.setText(""+value_scroll);
				
			}
			@Override
			public void actionPerformed(ActionEvent e) {
				int count=0;
				// TODO Auto-generated method stub
				System.out.println(value_scroll);
				//System.out.println("clicked");
				//Double[] values_s = get();
				for(int i = 0 ; i < 15; i ++)
				{
					if(values_set[i] <= value_scroll)
					{
						count++;
						
					}
				}
				display2(path_set,values_set,count);
				
				
				
			}
			
}
