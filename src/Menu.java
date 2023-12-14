import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Menu extends JPanel implements ActionListener, Runnable {

    JFrame frame = new JFrame("Estaciones");
    FallingObj iO = new FallingObj("recursos\\snow.png",1,1);
    FallingObj vO = new FallingObj("recursos\\leaf.png",1,1);
    FallingObj oO = new FallingObj("recursos\\leaves.png",1,1);
    FallingObj pO = new FallingObj("recursos\\daisy.png",1,1);
    Estacion iE = new Estacion("recursos\\BG1.png",iO);
    Estacion vE = new Estacion("recursos\\BG2.png",vO);
    Estacion oE = new Estacion("recursos\\BG3.png",oO);
    Estacion pE = new Estacion("recursos\\BG4.png",pO);

    Estacion current = iE;

    JButton bSalir = new JButton();
    JButton bInv = new JButton();
    JButton bOto = new JButton();
    JButton bVer = new JButton();
    JButton bPrim = new JButton();
    ImageIcon img = new ImageIcon(iE.getDir());
    JLabel im = new JLabel(img);
    JPanel pane = new JPanel();
    SpinnerModel model = new SpinnerNumberModel(1, 1, 99, 1);
    SpinnerModel model2 = new SpinnerNumberModel(1, 1, 10, 1);
    JSpinner spinner = new JSpinner(model);
    JSpinner spinner2 = new JSpinner(model2);
    JButton submit = new JButton();
    JButton submit2 = new JButton();
    Random r = new Random();
    int[] cant = {r.nextInt(800)};
    int[] canty = {r.nextInt(-500, 0)};
    boolean[] LR = {true};

    int x = 0;
    int aux = 1;
    int y = 0;
    int xVelocity = 1;
    int yVelocity = 1;
    Timer time;

    //El contructor ajusta todos los parametros de cada elemnto del GUI
    Menu(){
        time = new Timer(10, this);
        time.start();

        pane.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(0,0,800,500);
        this.setBounds(0,0,800,500);
        frame.setResizable(false);
        pane.setBounds(80,350 ,600,60);
        bSalir.setBounds(460,22, 100, 30);
        this.setVisible(true);
        this.isDoubleBuffered();
        frame.setLocationRelativeTo(null);

        bInv.setBorder(null);
        bVer.setBorder(null);
        bOto.setBorder(null);
        bPrim.setBorder(null);
        bSalir.setBorder(null);
        submit.setBorder(null);
        submit2.setBorder(null);

        bInv.setIcon(new ImageIcon("recursos\\btnInv.png"));
        bVer.setIcon(new ImageIcon("recursos\\btnVer.png"));
        bOto.setIcon(new ImageIcon("recursos\\btnOto.png"));
        bPrim.setIcon(new ImageIcon("recursos\\btnPri.png"));
        submit.setIcon(new ImageIcon("recursos\\Parti.png"));
        submit2.setIcon(new ImageIcon("recursos\\Ind.png"));
        bSalir.setIcon(new ImageIcon("recursos\\Salir.png"));

        pane.setBackground(new Color(0,0,0,65));
        submit2.setBackground(new Color(0,0,0,0));
        submit.setBackground(new Color(0,0,0,0));
        bSalir.setBackground(new Color(0,0,0,0));

        im.setVisible(true);
        im.setBounds(0,0, 800,500);

        frame.setVisible(true);

        bSalir.addActionListener(this);
        submit.addActionListener(this);
        bInv.addActionListener(this);
        bVer.addActionListener(this);
        bOto.addActionListener(this);
        bPrim.addActionListener(this);
    }

    //El Hilo llama al Inicio del Programa
    public void Start(){
        frame.add(this);
        this.add(pane);
        pane.add(bInv);
        pane.add(bVer);
        pane.add(bOto);
        pane.add(bPrim);
        pane.add(spinner);
        pane.add(submit2);
        pane.add(spinner2);
        pane.add(submit);
        pane.add(bSalir);

    }

    //Pinta tanto el fondo como los objetos en pantalla
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(img.getImage(),0,0,null);
        for (int i = 0; i<cant.length; i++) {
            g2D.drawImage(new ImageIcon(current.getObj().getDir()).getImage(), cant[i], canty[i], null);
        }
    }

    //Revisa cada acción, tanto los frames como botones
    @Override
    public void actionPerformed(ActionEvent e) {
        //Funciones para cada Frame
        if(current == iE) {
            Thread Invierno = Invierno();
            Invierno.start();
        }if(current == vE) {
            Thread Verano = Verano();
            Verano.start();
        }if(current == oE || current == pE) {
            Thread OtoYPrim = OtoYPrim();
            OtoYPrim.start();
        }

        repaint();

        //Funciones para cada Boton
        if (e.getSource() == bSalir){
            System.exit(0);
        }if(e.getSource() == submit){
            Thread main = MainThread();
            main.start();
        }if (e.getSource() == bInv) {
            Thread tI = InvChange();
            tI.start();
        }if (e.getSource() == bVer){
            Thread tV = VerChange();
            tV.start();
        }if (e.getSource() == bOto){
            Thread tO = OtoChange();
            tO.start();
        }if (e.getSource() == bPrim){
            Thread tP = PrimChange();
            tP.start();
        }
    }

    //Hilo del boton Submit
    private Thread MainThread() {
        Runnable Thread = () -> {
            xVelocity = (int) spinner2.getValue();
            yVelocity = (int) spinner2.getValue();
            int[] aux = new int[(int) spinner.getValue()];
            int[] auxy = new int[(int) spinner.getValue()];
            boolean[] LorR = new boolean[(int) spinner.getValue()];
            if(current == iE || current == oE || current == pE) {
                for (int i = 0; i < aux.length; i++) {
                    aux[i] = r.nextInt(800);
                    auxy[i] = r.nextInt(-500, 0);
                    LorR[i] = r.nextBoolean();
                }
            }
            if(current == vE){
                for (int i = 0; i < aux.length; i++) {
                    aux[i] = r.nextInt(-800, 0);
                    auxy[i] = r.nextInt(500);

                }
            }
            LR = LorR;
            cant = aux;
            canty = auxy;
            repaint();
        };
        return new Thread(Thread);
    }

    //Todos los Hilos de los botones de Estacion
    private Thread InvChange() {
        Runnable Thread = () -> {
            img = new ImageIcon(iE.getDir());
            current = iE;
            int[] aux = new int[(int) spinner.getValue()];
            int[] auxy = new int[(int) spinner.getValue()];
            for (int i = 0; i < cant.length; i++) {
                aux[i] = r.nextInt(800);
                auxy[i] = r.nextInt(-500, 0);
            }
            cant = aux;
            canty = auxy;
            repaint();
        };
        return new Thread(Thread);
    }
    private Thread VerChange() {
        Runnable Thread = () -> {
            img = new ImageIcon(vE.getDir());
            current = vE;
            int[] aux = new int[(int) spinner.getValue()];
            int[] auxy = new int[(int) spinner.getValue()];
            for(int i = 0; i<cant.length; i++){
                aux[i] = r.nextInt(-800, 0);
                auxy[i] = r.nextInt(500);
            }
            cant = aux;
            canty = auxy;
            repaint();
        };
        return new Thread(Thread);
    }

    private Thread OtoChange() {
        Runnable Thread = () -> {
            img = new ImageIcon(oE.getDir());
            current = oE;
            int[] aux = new int[(int) spinner.getValue()];
            int[] auxy = new int[(int) spinner.getValue()];
            boolean[] LorR = new boolean[(int) spinner.getValue()];
            for(int i = 0; i<cant.length; i++){
                aux[i] = r.nextInt(800);
                auxy[i] = r.nextInt(-500, 0);
                LorR[i] = r.nextBoolean();
            }
            LR = LorR;
            cant = aux;
            canty = auxy;
            repaint();
        };
        return new Thread(Thread);
    }

    private Thread PrimChange() {
        Runnable Thread = () -> {
            img = new ImageIcon(pE.getDir());
            current = pE;
            int[] aux = new int[(int) spinner.getValue()];
            int[] auxy = new int[(int) spinner.getValue()];
            boolean[] LorR = new boolean[(int) spinner.getValue()];
            for(int i = 0; i<cant.length; i++){
                aux[i] = r.nextInt(800);
                auxy[i] = r.nextInt(-500, 0);
                LorR[i] = r.nextBoolean();
            }
            LR = LorR;
            cant = aux;
            canty = auxy;
            repaint();
        };
        return new Thread(Thread);
    }

    //Hilos de las Estaciones
    private Thread Invierno() {
        Runnable Thread = () -> {
            for (int i = 0; i < cant.length; i++) {
                if (canty[i] > 500) {
                    canty[i] = r.nextInt(-500, 0);
                }
                canty[i] = canty[i] + yVelocity;
            }
        };
        return new Thread(Thread);
    }
    private Thread Verano() {
        Runnable Thread = () -> {
            for (int i = 0; i < cant.length; i++) {
                if (cant[i] > 800) {
                    cant[i] = r.nextInt(-800, 0);
                }
                cant[i] = cant[i] + xVelocity;
            }
        };
        return new Thread(Thread);
    }
    private Thread OtoYPrim() {
        Runnable Thread = () -> {
            for (int i = 0; i < cant.length; i++) {
                if (canty[i] > 500 ) {
                    canty[i] = r.nextInt(-500, 0);
                }
                if (LR[i] && cant[i] > 800) {
                    canty[i] = r.nextInt(-500, 0);
                    cant[i] = r.nextInt(800);
                }
                if (!LR[i] && cant[i] < 0) {
                    canty[i] = r.nextInt(-500, 0);
                    cant[i] = r.nextInt(1, 800);
                }
                canty[i] = canty[i] + yVelocity;
                if(LR[i]){
                    cant[i] += xVelocity;
                }else{
                    cant[i] -= xVelocity;
                }
                if(r.nextInt(10000) > 9998){
                    LR[i] = r.nextBoolean();
                }
            }
        };
        return new Thread(Thread);
    }


    @Override
    public void run() {
        Start();

    }
}
