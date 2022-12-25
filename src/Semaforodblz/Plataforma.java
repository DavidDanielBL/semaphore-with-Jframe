/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Semaforodblz;


import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.Toolkit;
import java.awt.Transparency;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Plataforma extends Canvas implements Escenario {

    private SonidoCache sonido;
    private BufferStrategy estrategia;
    private long usedTime;
    private ImagenCache imagencache;
    private ArrayList Objetos;
    private Buffer buffer;
    private Productor p;
    private int k;
    private int h;
    private Thread hilo;
    private int t;
    private int l;
    public Color color;
    private BufferedImage Fondo,  Fondo_Tile;
    private int FondoY;
    public boolean FIN = false,consemaforo,sinsemaforo;
    private DefaultListModel entrando;
    private DefaultListModel comiendo;
    private DefaultListModel saliendo;
    private Salon salon;
    private ScheduledExecutorService invoker;//mmmm
    private Random random = new Random();
    private int nextTurno = 0;
    JPanel p1, p2, p3;
    protected static Semaphore oFinP1,  oFinP3;
    private int ready=0;
    

    public Plataforma() {
  MetalLookAndFeel.setCurrentTheme(new EstiloHalloween());
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame ventana = new JFrame("^^^^^^METODO DE SINCRONIZACION DEL SEMAFORO^^^^^^^");

        imagencache = new ImagenCache();
        sonido = new SonidoCache();
        Objetos = new ArrayList();
        hilo = new Thread();
       
        JLabel llegadas = new JLabel("LLEGADA Y EN ESPERA (COLA)");
        JLabel consumo = new JLabel("CONSUMO PARA 5 CONSUMIDORES");
        JLabel salida = new JLabel("SALIDA DE CONSUMIDORES");
        llegadas.setBounds(10, 485, 200, 50);
        llegadas.setForeground(Color.green);
        consumo.setBounds(350, 485, 250, 50);
        consumo.setForeground(Color.green);
        salida.setBounds(670, 485, 250, 50);
        salida.setForeground(Color.green);
        JButton reiniciar = new JButton("Iniciar");
        JButton ssemaforo = new JButton("Ssemafo");
        JButton csemaforo = new JButton("Csemafo");
        JList lbEntrando = new JList();
        JList lbComiendo = new JList();
        JList lbSaliendo = new JList();
        lbEntrando.setModel(new javax.swing.AbstractListModel() {
        
            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
          lbSaliendo.setModel(new javax.swing.AbstractListModel() {

            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });
         lbEntrando.setModel(new javax.swing.AbstractListModel() {

            String[] strings = {"Item 1", "Item 2", "Item 3", "Item 4", "Item 5"};

            public int getSize() {
                return strings.length;
            }

            public Object getElementAt(int i) {
                return strings[i];
            }
        });

        p1 = new JPanel();
       
       
        p2 = new JPanel();
        
        p3 = new JPanel();

              
        lbEntrando.setBackground(Color.black);
        lbEntrando.setForeground(Color.yellow);
        lbComiendo.setBackground(Color.black);
        lbComiendo.setForeground(Color.yellow);
        lbSaliendo.setBackground(Color.black);
        lbSaliendo.setForeground(Color.yellow);
        
        JScrollPane pScroll1 = new JScrollPane(p1, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pScroll1.setBounds(10, 520, 280, 200);
        pScroll1.setBackground(Color.yellow);
    
        JScrollPane pScroll2 = new JScrollPane(p2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pScroll2.setBounds(300, 520, 300, 200);
        pScroll2.setBackground(Color.yellow);
        
        JScrollPane pScroll3 = new JScrollPane(p3, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        pScroll3.setBounds(620, 520, 300, 200);
        pScroll3.setBackground(Color.yellow);
        
        pScroll1.setViewportView(lbEntrando);
        entrando = new DefaultListModel();
        lbEntrando.setModel(entrando);
        //////////////
         pScroll2.setViewportView(lbComiendo);
        comiendo = new DefaultListModel();
        lbComiendo.setModel(comiendo);
        ////////////
         pScroll3.setViewportView(lbSaliendo);
        saliendo = new DefaultListModel();
        lbSaliendo.setModel(saliendo);
        
        
       
        
        //  p2.setVisible(true);
        //  p1.add(p2);
        reiniciar.setBounds(920, 650, 100, 50);
        reiniciar.addActionListener(new iniciarsemaforo());
        ssemaforo.setBounds(920, 580, 100, 50);
        ssemaforo.addActionListener(new sinsemaforo());
        
        csemaforo.setBounds(920, 520, 100, 50);
        csemaforo.addActionListener(new consemaforo());
        
        // reiniciar.setVisible(true);
       
        ventana.setBounds(0, 0, Escenario.Ancho, Escenario.Altura);
        ventana.add(reiniciar);
        ventana.add(ssemaforo);
        ventana.add(csemaforo);
        ventana.add(pScroll1);
        ventana.add(pScroll2);
        ventana.add(pScroll3);
        ventana.add(llegadas);
        ventana.add(consumo);
        ventana.add(salida);
        JPanel panel = (JPanel) ventana.getContentPane();
        panel.setBackground(Color.BLACK);
        setBounds(0, 0, 1024, 500);
        panel.setPreferredSize(new Dimension(Escenario.Ancho, Escenario.Altura));
        panel.setLayout(null);
     
        panel.add(this);
        ventana.setVisible(true);
        ventana.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }
        });

        ventana.setResizable(false);
        createBufferStrategy(2);
        estrategia = getBufferStrategy();
        requestFocus();


        setIgnoreRepaint(true);

        BufferedImage cursor = imagencache.Crea_Compatibilidad(10, 10, Transparency.BITMASK);
        Toolkit t = Toolkit.getDefaultToolkit();
        Cursor c = t.createCustomCursor(cursor, new Point(5, 5), "null");
        setCursor(c);
    }

    public void IniciarMundo() {
           


        Objetos = new ArrayList();

        for (int i = 0; i < 3; i++) {

            Semaforo m = new Semaforo(this);
            if (i == 0) {
                m.setX(350);
                m.setY(50);
            }
            if (i == 1) {
                m.setX(10);
                m.setY(200);
            }
            if (i == 2) {
                m.setX(900);
                m.setY(200);
            }
            //m.setVelocidadx((int) (Math.random() * 20 - 10));
          Objetos.add(m);


        }

         p = new Productor(this);
        p.setX(10);
        p.setY(400);

        //m.setVelocidadx((int) (Math.random() * 20 - 10));
       Objetos.add(p);

        Consumidor c = new Consumidor(this);
        c.setX(750);
        c.setY(300);
        Objetos.add(c);

        buffer = new Buffer(this);


        buffer.setX(280);
        buffer.setY(225);

        Fondo_Tile = imagencache.getImagen("matrix2.jpg");
        Fondo = imagencache.Crea_Compatibilidad(Escenario.Ancho, Escenario.Altura + Fondo_Tile.getHeight(),
                Transparency.OPAQUE);
        Graphics2D g = (Graphics2D) Fondo.getGraphics();
        g.setPaint(new TexturePaint(Fondo_Tile,
                new Rectangle(0, 0, Fondo_Tile.getWidth(), Fondo_Tile.getHeight())));
        g.fillRect(0, 0, Fondo.getWidth(), Fondo.getHeight());
        FondoY = Fondo_Tile.getHeight();

        sonido.loopSound("li.wav");

    }

    public void addActor(Objeto a) {
        Objetos.add(a);
    }

    public Buffer getJugador() {
        return buffer;
    }

    public void ActualizarMundoconSemaforo() {
        int i = 0;
        while (i < Objetos.size()) {
            Objeto m = (Objeto) Objetos.get(i);
            if (m.EsMarca_Elimina()) {
                Objetos.remove(i);

            } else {
               
               m.accion();
                i++;
            }
        }
    
       
         
    }
 public void ActualizarMundosinSemaforo() {
        int i = 0;
        while (i < Objetos.size()) {
            Objeto m = (Objeto) Objetos.get(i);
            if (m.EsMarca_Elimina()) {
                Objetos.remove(i);

            } else {
                
                m.accionsinsemaforo();
                i++;
                
            }
        }
    
       
         
    }
    public void Comprobar_Estado_choque() {

        Graphics2D g = (Graphics2D) Fondo.getGraphics();
        Rectangle Buffer_Limite = buffer.Limite();
        for (int i = 0; i < Objetos.size(); i++) {
            Objeto a1 = (Objeto) Objetos.get(i);
            Rectangle r1 = a1.Limite();
            if (r1.intersects(Buffer_Limite)) {
                p.Llegada(a1);
                a1.Llegada(p);


            }

            for (int j = i + 1; j < Objetos.size(); j++) {
                Objeto a2 = (Objeto) Objetos.get(j);
                Rectangle r2 = a2.Limite();
                if (r1.intersects(r2)) {
                    a1.Llegada(a2);
                    a2.Llegada(a1);
                }
            }
        }
    }

    public void PintarProductor(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
         color = new Color(27, 74, 128);
        g.setPaint(color);
        g.drawString("PRODUCTOR", 10, 400);
        g.setPaint(Color.green);



    }

    public void PintarConsumidor(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        color = new Color(153, 44, 57);
        g.setPaint(color);
        g.drawString("CONSUMIDOR", 730, 300);
        g.setPaint(Color.green);



    }
  
      public void Pintarsinsemaforo(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        color = new Color(153, 44, 57);
        g.setPaint(Color.YELLOW);
        g.drawString("PROBLEMA DE EXCLUSION MUTUA ", 250, 30);
      //  g.setPaint(Color.YELLOW);


    }
          public void Pintarconsemaforo(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
        color = new Color(153, 44, 57);
          g.setPaint(Color.YELLOW);
        g.drawString("SINCRONIZANDO CON SEMAFOROS ", 250, 30);
      //  g.setPaint(Color.green);


    }
     

    public void PintarBuffer(Graphics2D g) {
        g.setFont(new Font("Arial", Font.BOLD, 20));
         color = new Color(153, 142, 44);
        g.setPaint(color);
        g.drawString("BUFFER", 350, 220);
        g.setPaint(Color.green);



    }

    public void PintarAlmacen(Graphics2D g) {
        g.setPaint(Color.white);
        g.fillRect(281, 300, Buffer.MAX_ALMACEN+10, 20);
        g.setPaint(Color.gray);
        g.fillRect(281 + p.MAX_ALMACEN- p.getZonacritica(), 300, p.getZonacritica()+10, 20);
        g.setFont(new Font("Arial", Font.BOLD, 20));


    }

    public void PintarPosicion(Graphics2D g) {

        PintarProductor(g);
        PintarConsumidor(g);
        PintarBuffer(g);
        PintarAlmacen(g);



    }
     public void ready() {
        Graphics2D g = (Graphics2D) estrategia.getDrawGraphics();
        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Ready", Escenario.Ancho / 2 - 50, Escenario.Altura / 2);
        estrategia.show();
    }

    public void PintarMundo() {

        Graphics2D g = (Graphics2D) estrategia.getDrawGraphics();

        g.drawImage(Fondo, 0, 0, Escenario.Ancho, Escenario.Altura, 0,
                FondoY, Escenario.Ancho, FondoY + Escenario.Altura, this);
        for (int i = 0; i < Objetos.size(); i++) {
            Objeto m = (Objeto) Objetos.get(i);
            m.pintar(g);
        }

        buffer.pintar(g);
        PintarPosicion(g);
        
        if (sinsemaforo==true  && consemaforo==false);
        Pintarsinsemaforo(g);
        estrategia.show();
    }
     public void PintarMundo2() {

        Graphics2D g = (Graphics2D) estrategia.getDrawGraphics();

        g.drawImage(Fondo, 0, 0, Escenario.Ancho, Escenario.Altura, 0,
                FondoY, Escenario.Ancho, FondoY + Escenario.Altura, this);
        for (int i = 0; i < Objetos.size(); i++) {
            Objeto m = (Objeto) Objetos.get(i);
            m.pintar(g);
        }

        buffer.pintar(g);
        PintarPosicion(g);
        
        if (sinsemaforo==true  && consemaforo==false);
        Pintarconsemaforo(g);
        estrategia.show();
    }

    
 
    
    
    
    public void comienzo() {
     //  zonacritica();
       // nozonacritica();
     //   Semforo sm= new Semforo();
     //sm.smgforo();
        usedTime = 1000;
        IniciarMundo();
   
        while (isVisible() && !FIN) {

            long tiempoinicio = System.currentTimeMillis();
            FondoY--;
            if (FondoY < 0) {
                FondoY = Fondo_Tile.getHeight();
            }
            if(consemaforo==true && sinsemaforo==false)
            {  
            ActualizarMundoconSemaforo();
            Comprobar_Estado_choque();
            PintarMundo2();
            ///ready();
            
            }
            if(sinsemaforo==true && consemaforo==false)
            { ActualizarMundosinSemaforo();
            Comprobar_Estado_choque();
            PintarMundo();
            }
            
            usedTime = System.currentTimeMillis() - tiempoinicio;
            do {
                Thread.yield();
            } while (System.currentTimeMillis() - tiempoinicio < 17);

        }

    }

    public ImagenCache getImagenCache() {
        return imagencache;
    }

    public SonidoCache getSonidoCache() {
        return sonido;
    }

    public class Cliente implements Runnable {

        private int turno;
        private SimpleDateFormat formatter = new SimpleDateFormat("hh:mm:ss");
        private Date horaLLegada = null;
        private Date horaSalida = null;
        private Date horaComer = null;

        public Cliente() {
            turno = ++nextTurno;
        }

        public int getTurno() {
            return turno;
        }

        public void setTurno(int turno) {
            this.turno = turno;
        }

        public Date getHoraComer() {
            return horaComer;
        }

        public void setHoraComer(Date horaComer) {
            this.horaComer = horaComer;
        }

        public Date getHoraLLegada() {
            return horaLLegada;
        }

        public void setHoraLLegada(Date horaLLegada) {
            this.horaLLegada = horaLLegada;
        }

        public Date getHoraSalida() {
            return horaSalida;
        }

        public void setHoraSalida(Date horaSalida) {
            this.horaSalida = horaSalida;
        }

        private String formatear(Date fecha) {
            try {
                return (fecha == null ? "***" : formatter.format(fecha));
            } catch (Exception exception) {
                return "***";
            }
        }

        @Override
        public String toString() {
            return "Consumidor " + turno + " llego : " + formatear(horaLLegada) +
                    " a  : " + formatear(horaComer) +
                    " salio a : " + formatear(horaSalida);
        }

        @Override
        public void run() {
            this.setHoraLLegada(new Date());
            entrando.addElement(this);
            try {
                salon.ocuparMesa(this);
                Thread.sleep(20000);
                salon.desocuparMesa(this);
            } catch (Exception e) {
            }

        }
    }

    private class Salon {

        private Semaphore semaforo;

        public Salon(int nroMesas) {
            semaforo = new Semaphore(nroMesas);
        }

        public void ocuparMesa(Cliente c) {
            try {
                semaforo.acquire();
            } catch (Exception exception) {
            }
            c.setHoraComer(new Date());
            entrando.removeElement(c);
            comiendo.addElement(c);
        }

        public void desocuparMesa(Cliente c) {
            c.setHoraSalida(new Date());
            comiendo.removeElement(c);
            saliendo.addElement(c);
            try {
                semaforo.release();
            } catch (Exception exception) {
            }
        }
    }
  public class iniciarsemaforo implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            salon = new Salon(5);
        
        
        invoker = Executors.newScheduledThreadPool(50);
        int llegada = 0;
         for (int i = 0; i < 50; i++) {
            llegada += random.nextInt(5);
            Cliente c=new Cliente();
            
            invoker.schedule( c, llegada, TimeUnit.SECONDS);
            
        }
        }

      
    }
  ///////////////////////////
  public class consemaforo implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
            
           consemaforo=true;
           sinsemaforo=false;
           
           
        }

      
    }
    //////////////////////////////////////
  public class sinsemaforo implements ActionListener {

        public void actionPerformed(ActionEvent evt) {
           sinsemaforo=true;
            consemaforo=false;
            
           
            
      
        }

      
    }
  
  
  
  
  
    public static void main(String[] args) {
        
    
        Plataforma david = new Plataforma();
       
        david.comienzo();

    }
}

