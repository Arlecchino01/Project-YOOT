import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayerPanel extends JPanel implements PlayerPanel_Interface{

    private List<Player> playerList;
    private List<PlayerCard> playerCards;

    

    @Override
    public void updatePlayerPanel(List<Player> playerList){
        this.playerList = playerList;
        int index = playerList.size() - 1;
        System.out.println(index);
        Player handlingPlayer = playerList.get(index);

        PlayerCard card = new PlayerCard(handlingPlayer);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridx = 0;
        gbc.gridy = index;
        
        add(card, gbc);

        System.out.println("d");
        revalidate();
        SwingUtilities.invokeLater(()-> repaint());
    }

    public PlayerPanel(){
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        
        
    }

    public void addPlayerCards(){

    }
    
}

class PlayerCard extends JPanel{
    private String name;
    public PlayerCard(Player player){
        setPreferredSize(new Dimension(300, 150));
        add(Box.createVerticalStrut(50));
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.name = player.getName();
        JLabel nameLabel = new JLabel(name);
        
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(nameLabel);
    }
}
