package monopoly;

/**
 * Project ID: 10 - Monopoly
 *
 * @author Benjamin Andres Fuentes Cavieres - 20104709
 * @author Sean Simpkins - 20105546
 */
public class runMonopoly {
    public static void main(String[] args) {
        
        MonopolyView view = new MonopolyView();
        MonopolyModel model = new MonopolyModel();
        
        model.addObserver(view);
        MonopolyController control = new MonopolyController(view, model);
    }
}
