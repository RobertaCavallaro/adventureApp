package adventureApp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class UserInterface extends JFrame implements Mappable
{

    private static final long serialVersionUID = 1L;
    private JLabel parkNameLabel;
    private JLabel mapLabel;
    private JComboBox<String> parkDropdown;
    private Map<String, String> parkImages;
    private static final String DEFAULT_MAP_IMAGE = "map.JPG";
    private Map<String, String> parkInfo;
    private Map<String, Integer> elevationData; 

    public UserInterface()
    {
        // Initialize park images
        parkImages = new HashMap<>();
        parkImages.put("Cabrillo National Park", "cabrillo.jpeg");
        parkImages.put("Pinnacles National Park", "pinnacles.jpeg");
        parkImages.put("Tennessee National Park", "tenneseevalley.jpg");
        parkImages.put("Death Valley National Park", "deathvalley.jpg");
        parkImages.put("Joshua Tree National Park", "joshuatree.jpg");


        // Initialize park information
        parkInfo = new HashMap<>();
        parkInfo.put("Cabrillo National Park", "Cabrillo National Park, located in San Diego, CA, offers beautiful coastal views and historical landmarks.");
        parkInfo.put("Pinnacles National Park", "Pinnacles National Park, known for its unique rock formations and caves, is located in central California.");
        parkInfo.put("Tennessee National Park", "Tennessee National Park offers diverse landscapes and scenic hiking trails.");
        parkInfo.put("Death Valley National Park", "Death Valley National Park is the hottest, driest, and lowest national park in the USA.");
        parkInfo.put("Joshua Tree National Park","Joshua Tree’s nearly 800,000 acres were set aside to protect the unique assembly of natural resources brought together by the junction of three of California’s ecosystems:\r\n"
        		+ "\r\n"
        		+ "The Colorado Desert, a western extension of the vast Sonoran Desert, occupies the southern and eastern parts of the park. It is characterized by stands of spike-like ocotillo plants and “jumping” cholla cactus.\r\n"
        		+ "The southern boundary of the Mojave Desert reaches across the northern part of the park. It is the habitat of the park’s namesake: the Joshua tree. Extensive stands of this peculiar looking plant are found in the western half of the park.\r\n"
        		+ "Joshua Tree’s third ecosystem is located in the westernmost part of the park above 4,000 feet (1,219 m). The Little San Bernardino Mountains provide habitat for a community of California juniper and pinyon pine.");

        
        // Initialize elevation data for each park 
        elevationData = new HashMap<>();
        elevationData.put("Cabrillo National Park", 30);
        elevationData.put("Pinnacles National Park", 50);
        elevationData.put("Tennessee National Park", 90);
        elevationData.put("Death Valley National Park", 10);
        elevationData.put("Joshua Tree National Park", 70);
        

        // Frame settings
        setTitle("Trail Adventure - National Parks");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top section for logo, title, and subtitle
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for the top panel

        // Logo settings
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("logo.jpg"));
        Image logoImage = logoIcon.getImage().getScaledInstance(200, 120, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImage)); // Corrected to use logoImage
        
        // Title and Subtitle
        JLabel titleLabel = new JLabel("National Park Service", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 26));
        JLabel subtitleLabel = new JLabel("Trail Adventure", JLabel.LEFT);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));

        // Add to top panel
        topPanel.add(logoLabel, BorderLayout.WEST);
        JPanel titlePanel = new JPanel(new GridLayout(2, 1));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 0));
        titlePanel.add(titleLabel);
        titlePanel.add(subtitleLabel);
        topPanel.add(titlePanel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);

        // Main content with filter and map sections
        JPanel mainContentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // Filter panel on the left
        JPanel filterPanel = new JPanel(new GridLayout(6, 1, 5, 5));
        filterPanel.setBorder(BorderFactory.createTitledBorder("Filter Trails"));
        filterPanel.setPreferredSize(new Dimension(200, 400));

        // Difficulty Checkboxes
        JCheckBox beginnerCheck = new JCheckBox("Beginner");
        JCheckBox intermediateCheck = new JCheckBox("Intermediate");
        JCheckBox expertCheck = new JCheckBox("Expert");

        filterPanel.add(new JLabel("Difficulty Level"));
        filterPanel.add(beginnerCheck);
        filterPanel.add(intermediateCheck);
        filterPanel.add(expertCheck);
        
     // Action listeners for difficulty checkboxes
        beginnerCheck.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (beginnerCheck.isSelected())
                {
                    intermediateCheck.setSelected(false);
                    expertCheck.setSelected(false);
                    displayImage("cabrillo.jpeg");  // Display image for Beginner
                    parkNameLabel.setText("Cabrillo National Park");  // Set label to park name
                    parkDropdown.setSelectedItem("Cabrillo National Park"); // Update dropdown selection
                }
                else
                {
                    setParkImage(null);  // Reset to default if unchecked
                    parkNameLabel.setText("National Parks Overview"); // Reset label text
                    parkDropdown.setSelectedItem(null); // Clear dropdown selection
                }
            }
        });

        intermediateCheck.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (intermediateCheck.isSelected()) {
                    beginnerCheck.setSelected(false);
                    expertCheck.setSelected(false);
                    displayImage("pinnacles.jpeg");  // Display image for Intermediate
                    parkNameLabel.setText("Pinnacles National Park");  // Set label to park name
                    parkDropdown.setSelectedItem("Pinnacles National Park"); // Update dropdown selection
                } 
                else
                {
                    setParkImage(null);  // Reset to default if unchecked
                    parkNameLabel.setText("National Parks Overview"); // Reset label text
                    parkDropdown.setSelectedItem(null); // Clear dropdown selection
                }
            }
        });

        expertCheck.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                if (expertCheck.isSelected())
                {
                    beginnerCheck.setSelected(false);
                    intermediateCheck.setSelected(false);
                    displayImage("tenneseevalley.jpg");  // Display image for Expert
                    parkNameLabel.setText("Tennessee National Park");  // Set label to park name
                    parkDropdown.setSelectedItem("Tennessee National Park"); // Update dropdown selection
                }
                else
                {
                    setParkImage(null);  // Reset to default if unchecked
                    parkNameLabel.setText("National Parks Overview"); // Reset label text
                    parkDropdown.setSelectedItem(null); // Clear dropdown selection
                }
            }
        });

        

        // Elevation Gain Slider
        filterPanel.add(new JLabel("Elevation Gain"));
        JSlider elevationSlider = new JSlider(JSlider.HORIZONTAL, 10, 100, 50);
        elevationSlider.setMajorTickSpacing(20);
        elevationSlider.setPaintTicks(true);
        elevationSlider.setPaintLabels(true);
        filterPanel.add(elevationSlider);
        elevationSlider.addChangeListener(e -> 
        {
            int selectedElevation = elevationSlider.getValue();
            updateParkBasedOnElevation(selectedElevation);
        });

        

        // Position filter panel
        gbc.gridx = 0;
        gbc.weightx = 0.3;
        mainContentPanel.add(filterPanel, gbc);

        
        // Map panel on the right
        JPanel mapPanel = new JPanel(new BorderLayout());
        mapPanel.setBorder(BorderFactory.createTitledBorder("Selected Park"));
        
        // Park name label
        parkNameLabel = new JLabel("Select a Park", JLabel.CENTER);
        parkNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mapPanel.add(parkNameLabel, BorderLayout.NORTH);

        // Map image label
        mapLabel = new JLabel();
        mapLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapPanel.add(mapLabel, BorderLayout.CENTER);
        setParkImage(null);

        // Park dropdown
        parkDropdown = new JComboBox<>(parkImages.keySet().toArray(new String[0]));
        parkDropdown.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        parkDropdown.addActionListener(e -> setParkImage((String) parkDropdown.getSelectedItem()));
        mapPanel.add(parkDropdown, BorderLayout.SOUTH);

        // Position map panel
        gbc.gridx = 1;
        gbc.weightx = 0.7;
        mainContentPanel.add(mapPanel, gbc);
        add(mainContentPanel, BorderLayout.CENTER);

        // Bottom fetch button panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton fetchButton = new JButton("Park Info");
        fetchButton.setPreferredSize(new Dimension(180, 40));
        fetchButton.setFont(new Font("Arial", Font.BOLD, 16));
        fetchButton.addActionListener(e -> fetchParkData((String) parkDropdown.getSelectedItem()));
        bottomPanel.add(fetchButton);
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Implementing the required method from Mappable interface
    @Override
    public String getImagePath()
    {
        return (String) parkDropdown.getSelectedItem();
    }

    public void setParkImage(String parkName)
    {
        if (parkName == null)
        {
            parkNameLabel.setText("National Parks Overview");
            displayImage(DEFAULT_MAP_IMAGE);
        }
        else
        {
            parkNameLabel.setText(parkName);
            String imagePath = parkImages.get(parkName);
            if (imagePath != null)
            {
                displayImage(imagePath);
            }
        }
    }
    
    //Exception Handling
    private void displayImage(String imagePath) 
    {
        try
        {
            ImageIcon parkIcon = new ImageIcon(getClass().getResource(imagePath));
            Image scaledImage = parkIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
            mapLabel.setIcon(new ImageIcon(scaledImage));
        }
        catch (NullPointerException e)
        {
            System.out.println("Image not found: " + imagePath);
            mapLabel.setIcon(null);
        }
    }


    private void fetchParkData(String parkName)
    {
        if (parkName != null && parkInfo.containsKey(parkName))
        {
            String info = parkInfo.get(parkName);
            JOptionPane.showMessageDialog(this, info, parkName + " Information", JOptionPane.INFORMATION_MESSAGE);
        } 
        else
        {
            JOptionPane.showMessageDialog(this, "Please select a park to fetch data.", "No Park Selected", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(() -> new UserInterface());
    }
    
    private void updateParkBasedOnElevation(int selectedElevation) 
    {
        // Loop through elevationData to find the first park within +/- 10 of selected elevation
        for (Map.Entry<String, Integer> entry : elevationData.entrySet()) 
        {
            int parkElevation = entry.getValue();
            String parkName = entry.getKey();

            // Check if park's elevation is within +/- 10 range of selectedElevation
            if (Math.abs(parkElevation - selectedElevation) <= 10)
            {
                // Update map, park label, and dropdown selection to this park
                setParkImage(parkName);
                parkNameLabel.setText(parkName);
                parkDropdown.setSelectedItem(parkName);
                return; // Stop after finding the first match
            }
        }

        // If no parks match, reset to default overview
        setParkImage(null);
        parkNameLabel.setText("National Parks Overview");
        parkDropdown.setSelectedItem(null);
    }

}
