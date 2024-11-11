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
    private JLabel parkNameLabel;  // Label for displaying the park name
    private JLabel mapLabel;       // Label for displaying the map image
    private JComboBox<String> parkDropdown; // Dropdown for park selection
    private Map<String, String> parkImages; // Map to store park names and image paths
    private static final String DEFAULT_MAP_IMAGE = "map.JPG"; 

    public UserInterface()
    {
        //PARK IMAGES
        parkImages = new HashMap<>();
        parkImages.put("Cabrillo National Park", "cabrillo.jpeg");
        parkImages.put("Pinnacles National Park", "pinnacles.jpeg");
        parkImages.put("Tennessee National Park", "tenneseevalley.jpg");
        parkImages.put("Death Valley National Park", "deathvalley.jpg");
        
        // Frame settings
        setTitle("Trail Adventure - National Parks");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top section for logo, title, and subtitle
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS)); // Y vertical layout for stacking

        // Logo settings positioning
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("logo.jpg"));
        Image logoImage = logoIcon.getImage();
        Image scaledLogo = logoImage.getScaledInstance(300, 200, Image.SCALE_SMOOTH); //scaling size for logo
        JLabel logoLabel = new JLabel(new ImageIcon(scaledLogo));
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT); // logo moved to left
        logoLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Title and Subtitle below the logo
        JLabel titleLabel = new JLabel("National Park Service", JLabel.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // modify margin left for title

        JLabel subtitleLabel = new JLabel("TRAIL ADVENTURE", JLabel.LEFT);
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        subtitleLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // modify margin left for subtitle

        // Display logo, title, and subtitle to top panel
        topPanel.add(logoLabel);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        topPanel.add(titleLabel);
        topPanel.add(subtitleLabel);

        // Top panel display north
        add(topPanel, BorderLayout.NORTH);

        // Main content panel using GridBagLayout
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  // adjust padding
        gbc.fill = GridBagConstraints.BOTH;

        // Filter panel position
        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new GridLayout(6, 1, 10, 10));  // 6 rows with 10px padding
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  // padding around panel

        JLabel filterTitleLabel = new JLabel("Filter Trails");
        filterTitleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        filterPanel.add(filterTitleLabel);

        JCheckBox beginnerCheck = new JCheckBox("Beginner");
        JCheckBox intermediateCheck = new JCheckBox("Intermediate");
        JCheckBox expertCheck = new JCheckBox("Expert");

        filterPanel.add(beginnerCheck);
        filterPanel.add(intermediateCheck);
        filterPanel.add(expertCheck);

        JLabel elevationLabel = new JLabel("Elevation Gain");
        filterPanel.add(elevationLabel);

        JSlider elevationSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 50);
        elevationSlider.setMajorTickSpacing(20);
        elevationSlider.setPaintTicks(true);
        elevationSlider.setPaintLabels(true);
        filterPanel.add(elevationSlider);

        // Adding filter panel to mainContentPanel (left side)
        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row
        gbc.weightx = 0.3;  // Give filter panel some width proportion
        gbc.weighty = 1.0;  // Take full height
        mainContentPanel.add(filterPanel, gbc);

        // Map Panel positioning
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new BorderLayout());

        // Label for the park name above the map
        parkNameLabel = new JLabel("Select a Park", JLabel.CENTER);
        parkNameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mapPanel.add(parkNameLabel, BorderLayout.NORTH);

        // Map image settings (default image)
        mapLabel = new JLabel();
        mapLabel.setHorizontalAlignment(SwingConstants.CENTER);  // Center the map image label
        mapPanel.add(mapLabel, BorderLayout.CENTER);
        setParkImage(null); // Load default map initially

        // Park selection dropdown
        parkDropdown = new JComboBox<>(parkImages.keySet().toArray(new String[0]));
        parkDropdown.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                String selectedPark = (String) parkDropdown.getSelectedItem();
                setParkImage(selectedPark);
            }
        });
        
        //dropdown to the top of the map panel
        mapPanel.add(parkDropdown, BorderLayout.SOUTH);

        // map panel to main content
        gbc.gridx = 1; // Second column
        gbc.gridy = 0; // First row
        gbc.weightx = 0.7;  // Width panel
        gbc.weighty = 1.0;  // Take full height
        mainContentPanel.add(mapPanel, gbc);

        add(mainContentPanel, BorderLayout.CENTER);

        // Bottom button to fetch park data
        JButton fetchButton = new JButton("Fetch Park Data");
        fetchButton.setPreferredSize(new Dimension(180, 40));
        fetchButton.setFont(new Font("Arial", Font.BOLD, 16));

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(fetchButton);

        // Adding bottom panel to the bottom
        add(bottomPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Method to set the park image and name based on selection, or load default image if none selected
    public void setParkImage(String parkName)
    {
        if (parkName == null)
        {
            parkNameLabel.setText("National Parks Overview");
            displayImage(DEFAULT_MAP_IMAGE);
        } 
        else
        {
            parkNameLabel.setText(parkName); // Update the park name label
            String imagePath = parkImages.get(parkName); // Get the image path from the map
            if (imagePath != null) {
                displayImage(imagePath);
            }
        }
    }

    //pATH FOR IMAGE DISPLAYING
    private void displayImage(String imagePath)
    {
        ImageIcon parkIcon = new ImageIcon(getClass().getResource(imagePath));
        Image scaledImage = parkIcon.getImage().getScaledInstance(400, 300, Image.SCALE_SMOOTH);
        mapLabel.setIcon(new ImageIcon(scaledImage));
    }

    @Override
    public String getImagePath()
    {
        return (String) parkDropdown.getSelectedItem();
    }

    // Main method to run the app
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new UserInterface();
            }
        });
    }
}
