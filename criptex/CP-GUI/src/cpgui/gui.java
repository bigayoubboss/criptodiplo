/*
 * Universidad Nacional de Colombia - Sede Bogotá *
 *
 *      David Montaño - damontanofe@unal.edu.co
 *      Laura Moreno - lvmorenoc@unal.edu.co
 *      Christian Rodriguez - carodriguezb@unal.edu.co
 *
 * Código liberado bajo licencia Creative Commons 3.0
 * http://creativecommons.org/licenses/by-nc-sa/3.0/
 */
package cpgui;

import ClassicalCryptography.*;
import BlockCryptography.*;
import HashFunctions.SHA1;
import Mac.*;
import PublicKeyCryptography.BellareRogawayCipher;
import PublicKeyCryptography.RSACipher;
import cpcommonmethods.BigramsOcurrence;
import cpcommonmethods.Code;
import cpcommonmethods.LettersOcurrence;
import cpcommonmethods.TrigramsOcurrence;
import jama.Matrix;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.table.*;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author damontanofe,lvmorenoc,carodriguezb
 */
public class gui extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    public javax.swing.JTextField[] arregloSustitucion;
    public String textoPlanoInicial = "";
    // Estas variables habiltian o deshabilitan algun metodo de cifrado
    private final boolean clasicosVisible = true;
    private final boolean bloquesVisible = true;
    private final boolean publicosVisible = true;
    private final boolean desplazamientoVisible = true;
    private final boolean sustitucionVisible = true;
    private final boolean affineVisible = true;
    private final boolean vigenereVisible = true;
    private final boolean hillVisible = true;
    private final boolean permutacionVisible = true;
    private final boolean DESSVisible = true;
    private final boolean TDESSVisible = true;
    private final boolean DESVisible = true;
    private final boolean TDESVisible = true;
    private final boolean AESVisible = true;
    private final boolean SPNVisible = true;
    private final boolean CBCMACVisible = true;
    private final boolean RSAVisible = true;
    private final boolean optimizacionRSAVisible = true;

    public void iniciarArregloSustitucion() {
        arregloSustitucion = new javax.swing.JTextField[]{claveSustitucionA,
                    claveSustitucionB, claveSustitucionC, claveSustitucionD,
                    claveSustitucionE, claveSustitucionF, claveSustitucionG,
                    claveSustitucionH, claveSustitucionI, claveSustitucionJ,
                    claveSustitucionK, claveSustitucionL, claveSustitucionM,
                    claveSustitucionN, claveSustitucionO, claveSustitucionP,
                    claveSustitucionQ, claveSustitucionR, claveSustitucionS,
                    claveSustitucionT, claveSustitucionU, claveSustitucionV,
                    claveSustitucionW, claveSustitucionX, claveSustitucionY,
                    claveSustitucionZ};
    }

    private void ocultarMetodos() {
        if (clasicosVisible) {
            if (!desplazamientoVisible) {
                panelMetodosClasicos.remove(panelMetodoDesplazamiento);
            }
            if (!sustitucionVisible) {
                panelMetodosClasicos.remove(panelMetodoSustitucion);
            }
            if (!affineVisible) {
                panelMetodosClasicos.remove(panelMetodoAffine);
            }
            if (!vigenereVisible) {
                panelMetodosClasicos.remove(panelMetodoVigenere);
            }
            if (!hillVisible) {
                panelMetodosClasicos.remove(panelMetodoHill);
            }
            if (!permutacionVisible) {
                panelMetodosClasicos.remove(panelMetodoPermutacion);
            }
        } else {
            panelClasesCifrados.remove(panelCifradoresClasicos);
        }
        if (bloquesVisible) {
            if (!SPNVisible) {
                panelMetodosBloque.remove(panelMetodosSPN);
            }
            if (!DESSVisible) {
                panelMetodosBloque.remove(panelMetodoDESS);
            }
            if (!TDESSVisible) {
                panelMetodosBloque.remove(panelMetodoTripleDESS);
            }
            if (!DESVisible) {
                panelMetodosBloque.remove(panelMetodoDES);
            }
            if (!TDESVisible) {
                panelMetodosBloque.remove(panelMetodoTDES);
            }
            if (!AESVisible) {
                panelMetodosBloque.remove(panelMetodoAES);
            }
            if (!CBCMACVisible) {
                panelMetodosBloque.remove(panelMetodoCBCMAC);
            }
        } else {
            panelClasesCifrados.remove(panelCifradoresBloque);
        }
        if (publicosVisible) {
            if (!RSAVisible) {
                panelMetodosPublicos.remove(panelMetodoRSA);
            } else {
                if (!optimizacionRSAVisible) {
                    tipoOptimizacionRSA.setVisible(false);
                    tipoOptimizacionRSA.setEnabled(false);
                }
            }
        } else {
            panelClasesCifrados.remove(panelCifradoresPublicos);
        }

    }

    /** Creates new form gui */
    public gui() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource(
                "/images/icon.png")).getImage());
        this.iniciarArregloSustitucion();
        ocultarMetodos();
        String fontFileName = "/lib/tahoma.ttf";
        InputStream is = this.getClass().getResourceAsStream(fontFileName);
        try {
            Font ttfBase;
            ttfBase = Font.createFont(Font.TRUETYPE_FONT, is);
            Font tahoma10 = ttfBase.deriveFont(Font.PLAIN, 10);
            Font tahoma11 = ttfBase.deriveFont(Font.PLAIN, 11);
            Font tahoma12 = ttfBase.deriveFont(Font.PLAIN, 12);

            // cajaTextoplano y cajaTextoCifrado
            cajaTextoCifrado.setFont(tahoma12);
            cajaTextoPlano.setFont(tahoma12);

            // Fuentes desplazamiento
            textoDescripcionDesplazamientoT.setFont(tahoma11);
            textoTipoCifradoDesplazamientoT.setFont(tahoma12);
            textoTipoCifradoDesplazamiento.setFont(tahoma11);
            textoFuncionamientoDesplazamiento.setFont(tahoma11);
            textoMasProbablesClavesDesplazamiento.setFont(tahoma11);
            textoMasProbablesClavesDesplazamientoR.setFont(tahoma11);
            muestraDesplazamientoA.setFont(tahoma10);
            muestraDesplazamientoDe.setFont(tahoma10);
            claveDesplazamientoCaracter.setFont(tahoma11);
            claveDesplazamientoNumero.setFont(tahoma11);
            tipoClaveDesplazamientoCaracter.setFont(tahoma11);
            tipoClaveDesplazamientoNumero.setFont(tahoma11);
            labelMuestraDesplazamientoDe.setFont(tahoma11);
            labelMuestraDesplazamientoa.setFont(tahoma11);

            // Fuentes sustitución
            textoDescripcionSustitucionT.setFont(tahoma11);
            textoTipoCifradoSustitucionT.setFont(tahoma12);
            textoTipoCifradoSustitucion.setFont(tahoma11);
            textoFuncionamientoSustitucion.setFont(tahoma11);
            textoSustitucionDesT.setFont(tahoma11);
            textoSustitucionDes.setAlignmentY(0.5f);
            labelMuestraSustitucionDe.setFont(tahoma11);
            labelClaveSustitucion.setFont(tahoma11);
            muestraSustitucionDe.setFont(tahoma10);
            claveSustitucion.setFont(tahoma10);
            labelClaveActualSust.setFont(tahoma11);

            // Fuentes afin
            textoDescripcionAffineT.setFont(tahoma11);
            textoTipoCifradoAffineT.setFont(tahoma12);
            textoTipoCifradoAffine.setFont(tahoma11);
            textoFuncionamientoAffine.setFont(tahoma11);
            textoAffineClave.setFont(tahoma11);
            textoMasProbablesClavesAffine.setFont(tahoma11);

            // Fuentes vigenere
            textoDescripcionVigenereT.setFont(tahoma11);
            textoTipoCifradoVigenereT.setFont(tahoma12);
            textoTipoCifradoVigenere.setFont(tahoma11);
            textoFuncionamientoVigenere.setFont(tahoma11);
            textoDescripcion10.setFont(tahoma11);
            textoResultadoVigenere.setFont(tahoma11);

            // Fuentes hill
            textoDescripcionHillT.setFont(tahoma11);
            textoTipoCifradoHillT.setFont(tahoma12);
            textoTipoCifradoHill.setFont(tahoma11);
            textoFuncionamientoHill.setFont(tahoma11);
            textoInfoHill.setFont(tahoma11);
            tipoClaveHill2.setFont(tahoma11);
            tipoClaveHill3.setFont(tahoma11);

            // Fuentes permutacion
            textoDescripcionPermutacionT.setFont(tahoma11);
            textoTipoCifradoPermutacionT.setFont(tahoma12);
            textoTipoCifradoPermutacion.setFont(tahoma11);
            textoFuncionamientoPermutacion.setFont(tahoma11);
            textoDescripcionPermutacionT1.setFont(tahoma11);
            clavePermutacionAlternativa.setFont(tahoma12);

            // Fuentes SPN
            textoDetallesSPN1.setFont(tahoma11);
            textoParejas.setFont(tahoma11);
            textoL1L3.setFont(tahoma11);
            textoClaveSPN.setFont(tahoma11);
            textoSustitucionSPN.setFont(tahoma11);
            textoPermutacionSPN.setFont(tahoma11);
            textoNumeroRondasSPN.setFont(tahoma11);
            nTexto.setFont(tahoma11);

            // Fuentes SDES
            textoDetallesDESS.setFont(tahoma11);
            textoDescripcionDESS.setFont(tahoma11);

            // Fuentes Triple SDES
            textoDetallesTripleDESS.setFont(tahoma11);
            textoDescripcionTripleDESS.setFont(tahoma11);

            // Fuentes DES
            textoDetallesDESS.setFont(tahoma11);
            textoDescripcionDESS.setFont(tahoma11);

            // Fuentes AES
            textoDetallesAES.setFont(tahoma11);
            textoDescripcionAES.setFont(tahoma11);

            // Fuentes CBCMAC
            textoDetallesCBCMAC.setFont(tahoma11);
            textoDescripcionCBCMAC.setFont(tahoma11);

            // Fuentes RSA
            tipoClaveRSAn.setFont(tahoma11);
            tipoClaveRSAd.setFont(tahoma11);
            tipoClaveRSAe.setFont(tahoma11);
            tipoClaveRSApq.setFont(tahoma11);
            textoClaveRSAq.setFont(tahoma11);
            tipoOptimizacionRSA.setFont(tahoma11);

        } catch (FontFormatException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(gui.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tipoClaveDesplazamiento = new javax.swing.ButtonGroup();
        tipoClaveHill = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        tipoClaveRSAab = new javax.swing.ButtonGroup();
        tipoClaveRSAnpq = new javax.swing.ButtonGroup();
        panelTitulo = new javax.swing.JLayeredPane();
        fondoTitulo = new javax.swing.JLabel();
        panelContenido = new javax.swing.JLayeredPane();
        tituloPlano = new javax.swing.JLabel();
        tituloCifrado = new javax.swing.JLabel();
        panelTextoPlano = new javax.swing.JScrollPane();
        cajaTextoPlano = new javax.swing.JTextPane();
        panelTextoCifrado = new javax.swing.JScrollPane();
        cajaTextoCifrado = new javax.swing.JTextPane();
        botonSalir = new javax.swing.JButton();
        botonEncriptar = new javax.swing.JButton();
        botonCriptoanalisis = new javax.swing.JButton();
        botonAcercaDe = new javax.swing.JButton();
        botonAbrirCifrado = new javax.swing.JButton();
        botonGuardarCifrado = new javax.swing.JButton();
        botonAbrirPlano = new javax.swing.JButton();
        botonLimpiarCifrado = new javax.swing.JButton();
        botonGuardarPlano = new javax.swing.JButton();
        botonLimpiarPlano1 = new javax.swing.JButton();
        panelClasesCifrados = new javax.swing.JTabbedPane();
        panelCifradoresClasicos = new javax.swing.JLayeredPane();
        panelMetodosClasicos = new javax.swing.JTabbedPane();
        panelMetodoDesplazamiento = new javax.swing.JLayeredPane();
        textoFuncionamientoDesplazamiento = new javax.swing.JLabel();
        textoTipoCifradoDesplazamiento = new javax.swing.JLabel();
        textoTipoCifradoDesplazamientoT = new javax.swing.JLabel();
        textoDescripcionDesplazamiento = new javax.swing.JScrollPane();
        textoDescripcionDesplazamientoT = new javax.swing.JTextArea();
        botonClaveDesplazamiento = new javax.swing.JButton();
        panelTipoClaveDesplazamiento = new javax.swing.JPanel();
        claveDesplazamientoNumero = new javax.swing.JTextField();
        tipoClaveDesplazamientoNumero = new javax.swing.JRadioButton();
        claveDesplazamientoCaracter = new javax.swing.JTextField();
        tipoClaveDesplazamientoCaracter = new javax.swing.JRadioButton();
        panelMuestraDesplazamiento = new javax.swing.JPanel();
        labelMuestraDesplazamientoDe = new javax.swing.JLabel();
        muestraDesplazamientoDe = new javax.swing.JTextField();
        labelMuestraDesplazamientoa = new javax.swing.JLabel();
        muestraDesplazamientoA = new javax.swing.JTextField();
        panelDescripcionDesplazamiento = new javax.swing.JScrollPane();
        cajaDescripcionDesplazamiento = new javax.swing.JTextArea();
        panelResultadosDesplazamiento = new javax.swing.JPanel();
        contenedorResultadosDesplazamiento = new javax.swing.JScrollPane();
        tablaResultadosDesplazamiento = new javax.swing.JTable();
        textoMasProbablesClavesDesplazamiento = new javax.swing.JLabel();
        textoMasProbablesClavesDesplazamientoR = new javax.swing.JLabel();
        contenedorResultadosDesplazamientoBigramas = new javax.swing.JScrollPane();
        tablaResultadosDesplazamientoBigramas = new javax.swing.JTable();
        contenedorResultadosDesplazamientoTrigramas = new javax.swing.JScrollPane();
        tablaResultadosDesplazamientoTrigramas = new javax.swing.JTable();
        textoDescripcionDesplazamiento1 = new javax.swing.JScrollPane();
        textoDescripcionDesplazamientoT1 = new javax.swing.JTextArea();
        botonLimpiarDesplazamiento = new javax.swing.JButton();
        panelMetodoSustitucion = new javax.swing.JLayeredPane();
        botonClaveSustitucion = new javax.swing.JButton();
        textoTipoCifradoSustitucion = new javax.swing.JLabel();
        textoFuncionamientoSustitucion = new javax.swing.JLabel();
        textoTipoCifradoSustitucionT = new javax.swing.JLabel();
        textoDescripcionSustitucion = new javax.swing.JScrollPane();
        textoDescripcionSustitucionT = new javax.swing.JTextArea();
        panelMuestraSustitucion = new javax.swing.JPanel();
        labelMuestraSustitucionDe = new javax.swing.JLabel();
        muestraSustitucionDe = new javax.swing.JTextField();
        labelClaveSustitucion = new javax.swing.JLabel();
        claveSustitucion = new javax.swing.JTextField();
        textoSustitucionDes = new javax.swing.JScrollPane();
        textoSustitucionDesT = new javax.swing.JTextArea();
        panelDescripcionSustitucion = new javax.swing.JScrollPane();
        cajaDescripcionSustitucion = new javax.swing.JTextArea();
        panelResultadosSustitucion = new javax.swing.JPanel();
        contenedorResultadoSusLetras = new javax.swing.JScrollPane();
        tablaResultadosSusLetras = new javax.swing.JTable();
        contenedorResultadosSusBigramas = new javax.swing.JScrollPane();
        tablaResultadosSusBigramas = new javax.swing.JTable();
        contenedorResultadosSusTrigramas = new javax.swing.JScrollPane();
        tablaResultadosSusTrigramas = new javax.swing.JTable();
        botonLimpiarSustitucion = new javax.swing.JButton();
        panelAnalisisManualSustitucion = new javax.swing.JPanel();
        textoMasProbablesClavesSustitucionR = new javax.swing.JLabel();
        labelSusManualA = new javax.swing.JLabel();
        claveSustitucionA = new javax.swing.JTextField();
        labelSusManualB = new javax.swing.JLabel();
        claveSustitucionB = new javax.swing.JTextField();
        labelSusManualC = new javax.swing.JLabel();
        claveSustitucionC = new javax.swing.JTextField();
        labelSusManualD = new javax.swing.JLabel();
        claveSustitucionD = new javax.swing.JTextField();
        labelSusManualE = new javax.swing.JLabel();
        claveSustitucionE = new javax.swing.JTextField();
        labelSusManualF = new javax.swing.JLabel();
        claveSustitucionF = new javax.swing.JTextField();
        labelSusManualG = new javax.swing.JLabel();
        claveSustitucionG = new javax.swing.JTextField();
        labelSusManualH = new javax.swing.JLabel();
        claveSustitucionH = new javax.swing.JTextField();
        labelSusManualI = new javax.swing.JLabel();
        claveSustitucionI = new javax.swing.JTextField();
        labelSusManualJ = new javax.swing.JLabel();
        claveSustitucionJ = new javax.swing.JTextField();
        labelSusManualK = new javax.swing.JLabel();
        claveSustitucionK = new javax.swing.JTextField();
        labelSusManualL = new javax.swing.JLabel();
        claveSustitucionL = new javax.swing.JTextField();
        labelSusManualM = new javax.swing.JLabel();
        claveSustitucionM = new javax.swing.JTextField();
        labelSusManualN = new javax.swing.JLabel();
        claveSustitucionN = new javax.swing.JTextField();
        labelSusManualO = new javax.swing.JLabel();
        claveSustitucionO = new javax.swing.JTextField();
        labelSusManualP = new javax.swing.JLabel();
        claveSustitucionP = new javax.swing.JTextField();
        labelSusManualQ = new javax.swing.JLabel();
        claveSustitucionQ = new javax.swing.JTextField();
        labelSusManualR = new javax.swing.JLabel();
        claveSustitucionR = new javax.swing.JTextField();
        labelSusManualS = new javax.swing.JLabel();
        claveSustitucionS = new javax.swing.JTextField();
        labelSusManualT = new javax.swing.JLabel();
        claveSustitucionT = new javax.swing.JTextField();
        labelSusManualU = new javax.swing.JLabel();
        claveSustitucionU = new javax.swing.JTextField();
        labelSusManualV = new javax.swing.JLabel();
        claveSustitucionV = new javax.swing.JTextField();
        labelSusManualW = new javax.swing.JLabel();
        claveSustitucionW = new javax.swing.JTextField();
        labelSusManualX = new javax.swing.JLabel();
        claveSustitucionX = new javax.swing.JTextField();
        labelSusManualY = new javax.swing.JLabel();
        claveSustitucionY = new javax.swing.JTextField();
        labelSusManualZ = new javax.swing.JLabel();
        claveSustitucionZ = new javax.swing.JTextField();
        botonLimpiarSustitucionManual = new javax.swing.JButton();
        labelClaveActualSust = new javax.swing.JLabel();
        panelMetodoAffine = new javax.swing.JLayeredPane();
        botonClaveAffine = new javax.swing.JButton();
        textoTipoCifradoAffine = new javax.swing.JLabel();
        textoFuncionamientoAffine = new javax.swing.JLabel();
        textoTipoCifradoAffineT = new javax.swing.JLabel();
        textoDescripcionAffine = new javax.swing.JScrollPane();
        textoDescripcionAffineT = new javax.swing.JTextArea();
        panelClaveAffine = new javax.swing.JPanel();
        claveAffineA = new javax.swing.JTextField();
        claveAffineB = new javax.swing.JTextField();
        textoAffine1 = new javax.swing.JLabel();
        textoAffine2 = new javax.swing.JLabel();
        textoAffine3 = new javax.swing.JLabel();
        textoAffineClave = new javax.swing.JLabel();
        panelDescripcionAffine = new javax.swing.JScrollPane();
        cajaDescripcionAffine = new javax.swing.JTextArea();
        panelResultadosAffine = new javax.swing.JPanel();
        contenedorResultadoAffineLetras = new javax.swing.JScrollPane();
        tablaResultadosAffineLetras = new javax.swing.JTable();
        contenedorResultadosAffineBigramas = new javax.swing.JScrollPane();
        tablaResultadosAffineBigramas = new javax.swing.JTable();
        contenedorResultadosAffineTrigramas = new javax.swing.JScrollPane();
        tablaResultadosAffineTrigramas = new javax.swing.JTable();
        textoMasProbablesClavesAffineR = new javax.swing.JLabel();
        textoMasProbablesClavesAffine = new javax.swing.JLabel();
        botonLimpiarAffine = new javax.swing.JButton();
        panelMetodoVigenere = new javax.swing.JLayeredPane();
        botonClaveVigenere = new javax.swing.JButton();
        textoTipoCifradoVigenere = new javax.swing.JLabel();
        textoFuncionamientoVigenere = new javax.swing.JLabel();
        textoTipoCifradoVigenereT = new javax.swing.JLabel();
        textoDescripcionVigenere = new javax.swing.JScrollPane();
        textoDescripcionVigenereT = new javax.swing.JTextArea();
        panelTipoClaveDesplazamiento1 = new javax.swing.JPanel();
        claveVigenere = new javax.swing.JTextField();
        textoDescripcion10 = new javax.swing.JLabel();
        panelDescripcionVigenere = new javax.swing.JScrollPane();
        cajaDescripcionVigenere = new javax.swing.JTextArea();
        panelResultadosVigenere = new javax.swing.JPanel();
        textoResultadoVigenere = new javax.swing.JLabel();
        textoResultadoVigenereClave = new javax.swing.JLabel();
        botonLimpiarVigenere = new javax.swing.JButton();
        panelMetodoHill = new javax.swing.JLayeredPane();
        infoHill = new javax.swing.JLabel();
        botonLimpiarHill = new javax.swing.JButton();
        botonClaveHill = new javax.swing.JButton();
        textoTipoCifradoHill = new javax.swing.JLabel();
        textoFuncionamientoHill = new javax.swing.JLabel();
        textoTipoCifradoHillT = new javax.swing.JLabel();
        textoDescripcionHill = new javax.swing.JScrollPane();
        textoDescripcionHillT = new javax.swing.JTextArea();
        textoescripcion6 = new javax.swing.JScrollPane();
        textoInfoHill = new javax.swing.JTextArea();
        panelDescripcionHillClave = new javax.swing.JScrollPane();
        cajaDescripcionHillClave = new javax.swing.JTextArea();
        panelClaveHill = new javax.swing.JPanel();
        claveHill1 = new javax.swing.JTextField();
        claveHill2 = new javax.swing.JTextField();
        claveHill3 = new javax.swing.JTextField();
        claveHill4 = new javax.swing.JTextField();
        claveHill5 = new javax.swing.JTextField();
        claveHill6 = new javax.swing.JTextField();
        claveHill7 = new javax.swing.JTextField();
        claveHill8 = new javax.swing.JTextField();
        claveHill9 = new javax.swing.JTextField();
        tipoClaveHill2 = new javax.swing.JRadioButton();
        tipoClaveHill3 = new javax.swing.JRadioButton();
        panelDescripcionHill = new javax.swing.JScrollPane();
        cajaDescripcionHill = new javax.swing.JTextArea();
        panelMetodoPermutacion = new javax.swing.JLayeredPane();
        botonLimpiarPermutacion = new javax.swing.JButton();
        botonClavePermutacion = new javax.swing.JButton();
        textoTipoCifradoPermutacion = new javax.swing.JLabel();
        textoFuncionamientoPermutacion = new javax.swing.JLabel();
        textoTipoCifradoPermutacionT = new javax.swing.JLabel();
        textoDescripcionPermutacion = new javax.swing.JScrollPane();
        textoDescripcionPermutacionT = new javax.swing.JTextArea();
        panelDescripcionPermutacion = new javax.swing.JScrollPane();
        cajaDescripcionPermutacion = new javax.swing.JTextArea();
        panelMuestraPermutacion = new javax.swing.JPanel();
        clavePermutacion = new javax.swing.JTextField();
        clavePermutacionAlternativa = new javax.swing.JCheckBox();
        textoDescripcionPermutacion1 = new javax.swing.JScrollPane();
        textoDescripcionPermutacionT1 = new javax.swing.JTextArea();
        infoPermutacion = new javax.swing.JLabel();
        panelCifradoresBloque = new javax.swing.JLayeredPane();
        panelMetodosBloque = new javax.swing.JTabbedPane();
        panelMetodoDESS = new javax.swing.JLayeredPane();
        panelDetallesDESS = new javax.swing.JScrollPane();
        textoDetallesDESS = new javax.swing.JTextArea();
        infoDESS = new javax.swing.JLabel();
        panelDescripcionDESS = new javax.swing.JScrollPane();
        cajaDescripcionDESS = new javax.swing.JTextArea();
        botonClaveDESS = new javax.swing.JButton();
        botonLimpiarDESS = new javax.swing.JButton();
        panelClaveDESS = new javax.swing.JPanel();
        claveDESS = new javax.swing.JTextField();
        textoDescripcionDESS = new javax.swing.JLabel();
        diagramaDESS = new javax.swing.JLabel();
        panelMetodoTripleDESS = new javax.swing.JLayeredPane();
        panelDetallesTripleDESS = new javax.swing.JScrollPane();
        textoDetallesTripleDESS = new javax.swing.JTextArea();
        infoTripleDESS = new javax.swing.JLabel();
        panelDescripcionTripleDESS = new javax.swing.JScrollPane();
        cajaDescripcionTripleDESS = new javax.swing.JTextArea();
        botonClaveTripleDESS = new javax.swing.JButton();
        botonLimpiarTripleDESS = new javax.swing.JButton();
        panelClaveTripleDESS = new javax.swing.JPanel();
        claveTripleDESS = new javax.swing.JTextField();
        textoDescripcionTripleDESS = new javax.swing.JLabel();
        diagramaTripleDESS = new javax.swing.JLabel();
        panelMetodoDES = new javax.swing.JLayeredPane();
        panelDetallesDES = new javax.swing.JScrollPane();
        textoDetallesDES = new javax.swing.JTextArea();
        infoDES = new javax.swing.JLabel();
        panelDescripcionDES = new javax.swing.JScrollPane();
        cajaDescripcionDES = new javax.swing.JTextArea();
        botonClaveDES = new javax.swing.JButton();
        botonLimpiarDES = new javax.swing.JButton();
        panelClaveDES = new javax.swing.JPanel();
        claveDES = new javax.swing.JTextField();
        textoDescripcionDES = new javax.swing.JLabel();
        diagramaDES = new javax.swing.JLabel();
        panelMetodoTDES = new javax.swing.JLayeredPane();
        panelDetallesTDES = new javax.swing.JScrollPane();
        textoDetallesTDES = new javax.swing.JTextArea();
        infoTDES = new javax.swing.JLabel();
        panelDescripcionTDES = new javax.swing.JScrollPane();
        cajaDescripcionTDES = new javax.swing.JTextArea();
        botonClaveTDES = new javax.swing.JButton();
        botonLimpiarTDES = new javax.swing.JButton();
        panelClaveTDES = new javax.swing.JPanel();
        claveTDES = new javax.swing.JTextField();
        textoDescripcionTDES = new javax.swing.JLabel();
        diagramaTDES = new javax.swing.JLabel();
        panelMetodoAES = new javax.swing.JLayeredPane();
        panelDetallesAES = new javax.swing.JScrollPane();
        textoDetallesAES = new javax.swing.JTextArea();
        infoAES = new javax.swing.JLabel();
        panelDescripcionAES = new javax.swing.JScrollPane();
        cajaDescripcionAES = new javax.swing.JTextArea();
        botonClaveAES = new javax.swing.JButton();
        botonLimpiarAES = new javax.swing.JButton();
        panelClaveAES = new javax.swing.JPanel();
        claveAES = new javax.swing.JTextField();
        textoDescripcionAES = new javax.swing.JLabel();
        diagramaAES = new javax.swing.JLabel();
        panelMetodosSPN = new javax.swing.JLayeredPane();
        panelDetallesSPN = new javax.swing.JScrollPane();
        textoDetallesSPN1 = new javax.swing.JTextArea();
        infoSPN = new javax.swing.JLabel();
        panelDescripcionSPN = new javax.swing.JScrollPane();
        cajaDescripcionSPN = new javax.swing.JTextArea();
        botonClaveSPN = new javax.swing.JButton();
        botonLimpiarSPN = new javax.swing.JButton();
        panelClaveSPN = new javax.swing.JPanel();
        claveSPN = new javax.swing.JTextField();
        textoClaveSPN = new javax.swing.JLabel();
        permutacionSPN = new javax.swing.JTextField();
        textoPermutacionSPN = new javax.swing.JLabel();
        sustitucionSPN = new javax.swing.JTextField();
        textoSustitucionSPN = new javax.swing.JLabel();
        numeroRondasSPN = new javax.swing.JTextField();
        textoNumeroRondasSPN = new javax.swing.JLabel();
        panelCriptoanalisis = new javax.swing.JPanel();
        textoParejas = new javax.swing.JLabel();
        rutaParejas = new javax.swing.JTextField();
        buscarParejas = new javax.swing.JButton();
        L1L2 = new javax.swing.JTextField();
        textoL1L3 = new javax.swing.JLabel();
        calcularL1L3 = new javax.swing.JButton();
        parejasPanel = new javax.swing.JPanel();
        botonMuestrasSPN = new javax.swing.JButton();
        nParejas = new javax.swing.JTextField();
        nTexto = new javax.swing.JLabel();
        panelMetodoCBCMAC = new javax.swing.JLayeredPane();
        muestraCBCMAC = new javax.swing.JLabel();
        infoCBCMAC = new javax.swing.JLabel();
        botonClaveCBCMAC = new javax.swing.JButton();
        botonLimpiarCBCMAC = new javax.swing.JButton();
        panelClaveCBCMAC = new javax.swing.JPanel();
        claveCBCMAC = new javax.swing.JTextField();
        textoDescripcionCBCMAC = new javax.swing.JLabel();
        panelDetallesCBCMAC = new javax.swing.JScrollPane();
        textoDetallesCBCMAC = new javax.swing.JTextArea();
        panelDescripcionCBCMAC = new javax.swing.JScrollPane();
        cajaDescripcionCBCMAC = new javax.swing.JTextArea();
        panelMetodoD1 = new javax.swing.JLayeredPane();
        infoD1 = new javax.swing.JLabel();
        panelClaveD1 = new javax.swing.JPanel();
        claveD1 = new javax.swing.JTextField();
        textoDescripcionD1 = new javax.swing.JLabel();
        panelDetallesD1 = new javax.swing.JScrollPane();
        textoDetallesD1 = new javax.swing.JTextArea();
        panelDescripcionD1 = new javax.swing.JScrollPane();
        cajaDescripcionD1 = new javax.swing.JTextArea();
        panelSecuenciaOcultamiento = new javax.swing.JPanel();
        botonSecuenciaOcultamiento = new javax.swing.JButton();
        secuenciaOcultamiento = new javax.swing.JTextField();
        panelTablaCifrado = new javax.swing.JPanel();
        botonTablaCifrado = new javax.swing.JButton();
        textoDescripcionSimboloD1 = new javax.swing.JLabel();
        tablaSimboloSeparador = new javax.swing.JTextField();
        botonClaveD1 = new javax.swing.JButton();
        botonLimpiarD1 = new javax.swing.JButton();
        panelCifradoresPublicos = new javax.swing.JLayeredPane();
        panelMetodosPublicos = new javax.swing.JTabbedPane();
        panelMetodoRSA = new javax.swing.JLayeredPane();
        panelClaveRSAab = new javax.swing.JPanel();
        tipoClaveRSAd = new javax.swing.JRadioButton();
        tipoClaveRSAe = new javax.swing.JRadioButton();
        cajaClaveRSAd = new javax.swing.JTextField();
        cajaClaveRSAe = new javax.swing.JTextField();
        panelClaveRSAnpq = new javax.swing.JPanel();
        tipoClaveRSAn = new javax.swing.JRadioButton();
        textoClaveRSAq = new javax.swing.JLabel();
        tipoClaveRSApq = new javax.swing.JRadioButton();
        cajaClaveRSAp = new javax.swing.JTextField();
        cajaClaveRSAq = new javax.swing.JTextField();
        cajaClaveRSAn = new javax.swing.JTextField();
        tipoOptimizacionRSA = new javax.swing.JCheckBox();
        botonLimpiarRSA = new javax.swing.JButton();
        botonClaveRSA = new javax.swing.JButton();
        diagramaRSA = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Criptex");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        fondoTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/fondoTitulo.png"))); // NOI18N
        fondoTitulo.setBounds(0, 0, 810, 50);
        panelTitulo.add(fondoTitulo, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelContenido.setFocusable(false);

        tituloPlano.setFont(new java.awt.Font("Tahoma", 1, 12));
        tituloPlano.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloPlano.setText("Texto Cifrado");
        tituloPlano.setFocusable(false);
        tituloPlano.setInheritsPopupMenu(false);
        tituloPlano.setBounds(440, 2, 350, 18);
        panelContenido.add(tituloPlano, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tituloCifrado.setFont(new java.awt.Font("Tahoma", 1, 12));
        tituloCifrado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloCifrado.setText("Texto Plano");
        tituloCifrado.setFocusable(false);
        tituloCifrado.setInheritsPopupMenu(false);
        tituloCifrado.setBounds(10, 2, 350, 18);
        panelContenido.add(tituloCifrado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelTextoPlano.setFocusable(false);

        cajaTextoPlano.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaTextoPlanoKeyTyped(evt);
            }
        });
        panelTextoPlano.setViewportView(cajaTextoPlano);

        panelTextoPlano.setBounds(10, 23, 350, 207);
        panelContenido.add(panelTextoPlano, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelTextoCifrado.setFocusable(false);

        cajaTextoCifrado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaTextoCifradoKeyTyped(evt);
            }
        });
        panelTextoCifrado.setViewportView(cajaTextoCifrado);

        panelTextoCifrado.setBounds(440, 23, 350, 207);
        panelContenido.add(panelTextoCifrado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salir.png"))); // NOI18N
        botonSalir.setToolTipText("Salir");
        botonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSalirActionPerformed(evt);
            }
        });
        botonSalir.setBounds(370, 185, 60, 45);
        panelContenido.add(botonSalir, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonEncriptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cifrar.png"))); // NOI18N
        botonEncriptar.setToolTipText("Cifrar");
        botonEncriptar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        botonEncriptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEncriptarActionPerformed(evt);
            }
        });
        botonEncriptar.setBounds(370, 23, 60, 45);
        panelContenido.add(botonEncriptar, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonCriptoanalisis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/criptoanalisis.png"))); // NOI18N
        botonCriptoanalisis.setToolTipText("Realizar CriptoAnálisis");
        botonCriptoanalisis.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonCriptoanalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCriptoanalisisActionPerformed(evt);
            }
        });
        botonCriptoanalisis.setBounds(370, 70, 60, 48);
        panelContenido.add(botonCriptoanalisis, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAcercaDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/creditos.png"))); // NOI18N
        botonAcercaDe.setToolTipText("Acerca de..");
        botonAcercaDe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAcercaDeActionPerformed(evt);
            }
        });
        botonAcercaDe.setBounds(370, 135, 60, 45);
        panelContenido.add(botonAcercaDe, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAbrirCifrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abrir.png"))); // NOI18N
        botonAbrirCifrado.setToolTipText("Abrir un archivo");
        botonAbrirCifrado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAbrirCifrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirCifradoActionPerformed(evt);
            }
        });
        botonAbrirCifrado.setBounds(540, 240, 40, 40);
        panelContenido.add(botonAbrirCifrado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonGuardarCifrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        botonGuardarCifrado.setToolTipText("Guardar a un archivo");
        botonGuardarCifrado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardarCifrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarCifradoActionPerformed(evt);
            }
        });
        botonGuardarCifrado.setBounds(600, 240, 40, 40);
        panelContenido.add(botonGuardarCifrado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonAbrirPlano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/abrir.png"))); // NOI18N
        botonAbrirPlano.setToolTipText("Abrir un archivo");
        botonAbrirPlano.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAbrirPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAbrirPlanoActionPerformed(evt);
            }
        });
        botonAbrirPlano.setBounds(110, 240, 40, 40);
        panelContenido.add(botonAbrirPlano, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarCifrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/limpiar.png"))); // NOI18N
        botonLimpiarCifrado.setToolTipText("Limpiar o reiniciar caja de texto");
        botonLimpiarCifrado.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarCifrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarCifradoActionPerformed(evt);
            }
        });
        botonLimpiarCifrado.setBounds(660, 240, 40, 40);
        panelContenido.add(botonLimpiarCifrado, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonGuardarPlano.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        botonGuardarPlano.setToolTipText("Guardar a un archivo");
        botonGuardarPlano.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonGuardarPlano.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarPlanoActionPerformed(evt);
            }
        });
        botonGuardarPlano.setBounds(170, 240, 40, 40);
        panelContenido.add(botonGuardarPlano, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarPlano1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/limpiar.png"))); // NOI18N
        botonLimpiarPlano1.setToolTipText("Limpiar o reiniciar caja de texto");
        botonLimpiarPlano1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarPlano1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarPlano1ActionPerformed(evt);
            }
        });
        botonLimpiarPlano1.setBounds(230, 240, 40, 40);
        panelContenido.add(botonLimpiarPlano1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosClasicos.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        panelMetodosClasicos.setFocusable(false);
        panelMetodosClasicos.setFont(new java.awt.Font("Tahoma", 0, 10));

        panelMetodoDesplazamiento.setBackground(new java.awt.Color(232, 232, 232));
        panelMetodoDesplazamiento.setFocusable(false);
        panelMetodoDesplazamiento.setName("desplazamiento"); // NOI18N

        textoFuncionamientoDesplazamiento.setText("Funcionamiento:");
        textoFuncionamientoDesplazamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textoFuncionamientoDesplazamiento.setFocusable(false);
        textoFuncionamientoDesplazamiento.setInheritsPopupMenu(false);
        textoFuncionamientoDesplazamiento.setVerifyInputWhenFocusTarget(false);
        textoFuncionamientoDesplazamiento.setBounds(25, 51, 80, 58);
        panelMetodoDesplazamiento.add(textoFuncionamientoDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoDesplazamiento.setText("Tipo de cifrado:");
        textoTipoCifradoDesplazamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textoTipoCifradoDesplazamiento.setFocusable(false);
        textoTipoCifradoDesplazamiento.setInheritsPopupMenu(false);
        textoTipoCifradoDesplazamiento.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoDesplazamiento.setBounds(25, 29, 90, 20);
        panelMetodoDesplazamiento.add(textoTipoCifradoDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoDesplazamientoT.setText("Monoalfabético por desplazamiento (z189)");
        textoTipoCifradoDesplazamientoT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textoTipoCifradoDesplazamientoT.setFocusable(false);
        textoTipoCifradoDesplazamientoT.setInheritsPopupMenu(false);
        textoTipoCifradoDesplazamientoT.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoDesplazamientoT.setBounds(110, 30, 270, 20);
        panelMetodoDesplazamiento.add(textoTipoCifradoDesplazamientoT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDescripcionDesplazamiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionDesplazamiento.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionDesplazamiento.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textoDescripcionDesplazamiento.setEnabled(false);
        textoDescripcionDesplazamiento.setFocusable(false);
        textoDescripcionDesplazamiento.setFont(new java.awt.Font("Tahoma", 0, 10));
        textoDescripcionDesplazamiento.setOpaque(false);
        textoDescripcionDesplazamiento.setVerifyInputWhenFocusTarget(false);

        textoDescripcionDesplazamientoT.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionDesplazamientoT.setColumns(150);
        textoDescripcionDesplazamientoT.setEditable(false);
        textoDescripcionDesplazamientoT.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionDesplazamientoT.setLineWrap(true);
        textoDescripcionDesplazamientoT.setRows(4);
        textoDescripcionDesplazamientoT.setTabSize(6);
        textoDescripcionDesplazamientoT.setText("Al cifrar, cada letra del alfabeto es reemplazada por la k-ésima que le sigue en orden, siendo k la clave utilizada. El paso a texto plano es realizado por FUERZA BRUTA.");
        textoDescripcionDesplazamientoT.setWrapStyleWord(true);
        textoDescripcionDesplazamientoT.setAutoscrolls(false);
        textoDescripcionDesplazamientoT.setBorder(null);
        textoDescripcionDesplazamientoT.setFocusable(false);
        textoDescripcionDesplazamientoT.setHighlighter(null);
        textoDescripcionDesplazamientoT.setOpaque(false);
        textoDescripcionDesplazamientoT.setRequestFocusEnabled(false);
        textoDescripcionDesplazamiento.setViewportView(textoDescripcionDesplazamientoT);

        textoDescripcionDesplazamiento.setBounds(110, 50, 270, 60);
        panelMetodoDesplazamiento.add(textoDescripcionDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveDesplazamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveDesplazamiento.setText("Generar una clave");
        botonClaveDesplazamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveDesplazamientoActionPerformed(evt);
            }
        });
        botonClaveDesplazamiento.setBounds(10, 130, 340, 30);
        panelMetodoDesplazamiento.add(botonClaveDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelTipoClaveDesplazamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese la clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelTipoClaveDesplazamiento.setFocusable(false);

        claveDesplazamientoNumero.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveDesplazamientoNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveDesplazamientoNumeroKeyTyped(evt);
            }
        });

        tipoClaveDesplazamiento.add(tipoClaveDesplazamientoNumero);
        tipoClaveDesplazamientoNumero.setSelected(true);
        tipoClaveDesplazamientoNumero.setText("número");
        tipoClaveDesplazamientoNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveDesplazamientoNumeroActionPerformed(evt);
            }
        });

        claveDesplazamientoCaracter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveDesplazamientoCaracter.setEnabled(false);
        claveDesplazamientoCaracter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveDesplazamientoCaracterKeyTyped(evt);
            }
        });

        tipoClaveDesplazamiento.add(tipoClaveDesplazamientoCaracter);
        tipoClaveDesplazamientoCaracter.setText("carácter");
        tipoClaveDesplazamientoCaracter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveDesplazamientoCaracterActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelTipoClaveDesplazamientoLayout = new javax.swing.GroupLayout(panelTipoClaveDesplazamiento);
        panelTipoClaveDesplazamiento.setLayout(panelTipoClaveDesplazamientoLayout);
        panelTipoClaveDesplazamientoLayout.setHorizontalGroup(
            panelTipoClaveDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoClaveDesplazamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTipoClaveDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoClaveDesplazamientoCaracter)
                    .addComponent(tipoClaveDesplazamientoNumero))
                .addGap(5, 5, 5)
                .addGroup(panelTipoClaveDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(claveDesplazamientoCaracter)
                    .addComponent(claveDesplazamientoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelTipoClaveDesplazamientoLayout.setVerticalGroup(
            panelTipoClaveDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoClaveDesplazamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTipoClaveDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(claveDesplazamientoNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoClaveDesplazamientoNumero))
                .addGap(5, 5, 5)
                .addGroup(panelTipoClaveDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoClaveDesplazamientoCaracter)
                    .addComponent(claveDesplazamientoCaracter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTipoClaveDesplazamiento.setBounds(10, 168, 135, 90);
        panelMetodoDesplazamiento.add(panelTipoClaveDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMuestraDesplazamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "El alfabeto original será desplazado", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelMuestraDesplazamiento.setFocusable(false);

        labelMuestraDesplazamientoDe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMuestraDesplazamientoDe.setText("De:");

        muestraDesplazamientoDe.setEditable(false);
        muestraDesplazamientoDe.setFont(new java.awt.Font("Tahoma", 0, 10));
        muestraDesplazamientoDe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        muestraDesplazamientoDe.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        labelMuestraDesplazamientoa.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMuestraDesplazamientoa.setText("a:");

        muestraDesplazamientoA.setEditable(false);
        muestraDesplazamientoA.setFont(new java.awt.Font("Tahoma", 0, 10));
        muestraDesplazamientoA.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelMuestraDesplazamientoLayout = new javax.swing.GroupLayout(panelMuestraDesplazamiento);
        panelMuestraDesplazamiento.setLayout(panelMuestraDesplazamientoLayout);
        panelMuestraDesplazamientoLayout.setHorizontalGroup(
            panelMuestraDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMuestraDesplazamientoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelMuestraDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelMuestraDesplazamientoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMuestraDesplazamientoDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMuestraDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(muestraDesplazamientoDe, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(muestraDesplazamientoA, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelMuestraDesplazamientoLayout.setVerticalGroup(
            panelMuestraDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMuestraDesplazamientoLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(panelMuestraDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMuestraDesplazamientoDe)
                    .addComponent(muestraDesplazamientoDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(panelMuestraDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMuestraDesplazamientoa)
                    .addComponent(muestraDesplazamientoA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelMuestraDesplazamiento.setBounds(150, 168, 240, 90);
        panelMetodoDesplazamiento.add(panelMuestraDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionDesplazamiento.setBorder(null);
        panelDescripcionDesplazamiento.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionDesplazamiento.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionDesplazamiento.setEnabled(false);
        panelDescripcionDesplazamiento.setFocusable(false);
        panelDescripcionDesplazamiento.setOpaque(false);
        panelDescripcionDesplazamiento.setPreferredSize(new java.awt.Dimension(370, 110));
        panelDescripcionDesplazamiento.setRequestFocusEnabled(false);
        panelDescripcionDesplazamiento.setVerifyInputWhenFocusTarget(false);

        cajaDescripcionDesplazamiento.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionDesplazamiento.setColumns(20);
        cajaDescripcionDesplazamiento.setEditable(false);
        cajaDescripcionDesplazamiento.setRows(4);
        cajaDescripcionDesplazamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionDesplazamiento.setEnabled(false);
        cajaDescripcionDesplazamiento.setFocusable(false);
        cajaDescripcionDesplazamiento.setOpaque(false);
        panelDescripcionDesplazamiento.setViewportView(cajaDescripcionDesplazamiento);

        panelDescripcionDesplazamiento.setBounds(10, 10, 380, 110);
        panelMetodoDesplazamiento.add(panelDescripcionDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelResultadosDesplazamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelResultadosDesplazamiento.setFocusable(false);

        tablaResultadosDesplazamiento.setAutoCreateRowSorter(true);
        tablaResultadosDesplazamiento.setFont(new java.awt.Font("Tahoma", 0, 10));
        tablaResultadosDesplazamiento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosDesplazamiento.setFocusable(false);
        tablaResultadosDesplazamiento.setRowSelectionAllowed(false);
        contenedorResultadosDesplazamiento.setViewportView(tablaResultadosDesplazamiento);

        textoMasProbablesClavesDesplazamiento.setText("Según el análisis de frecuencia la clave mas probable es:");
        textoMasProbablesClavesDesplazamiento.setFocusable(false);
        textoMasProbablesClavesDesplazamiento.setInheritsPopupMenu(false);

        textoMasProbablesClavesDesplazamientoR.setFont(new java.awt.Font("Tahoma", 1, 11));
        textoMasProbablesClavesDesplazamientoR.setFocusable(false);
        textoMasProbablesClavesDesplazamientoR.setInheritsPopupMenu(false);

        tablaResultadosDesplazamientoBigramas.setAutoCreateRowSorter(true);
        tablaResultadosDesplazamientoBigramas.setFont(new java.awt.Font("Tahoma", 0, 10));
        tablaResultadosDesplazamientoBigramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosDesplazamientoBigramas.setFocusable(false);
        tablaResultadosDesplazamientoBigramas.setRowSelectionAllowed(false);
        contenedorResultadosDesplazamientoBigramas.setViewportView(tablaResultadosDesplazamientoBigramas);

        tablaResultadosDesplazamientoTrigramas.setAutoCreateRowSorter(true);
        tablaResultadosDesplazamientoTrigramas.setFont(new java.awt.Font("Tahoma", 0, 10));
        tablaResultadosDesplazamientoTrigramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosDesplazamientoTrigramas.setFocusable(false);
        tablaResultadosDesplazamientoTrigramas.setRowSelectionAllowed(false);
        contenedorResultadosDesplazamientoTrigramas.setViewportView(tablaResultadosDesplazamientoTrigramas);

        javax.swing.GroupLayout panelResultadosDesplazamientoLayout = new javax.swing.GroupLayout(panelResultadosDesplazamiento);
        panelResultadosDesplazamiento.setLayout(panelResultadosDesplazamientoLayout);
        panelResultadosDesplazamientoLayout.setHorizontalGroup(
            panelResultadosDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResultadosDesplazamientoLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelResultadosDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResultadosDesplazamientoLayout.createSequentialGroup()
                        .addComponent(contenedorResultadosDesplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contenedorResultadosDesplazamientoBigramas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(contenedorResultadosDesplazamientoTrigramas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelResultadosDesplazamientoLayout.createSequentialGroup()
                        .addComponent(textoMasProbablesClavesDesplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoMasProbablesClavesDesplazamientoR, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelResultadosDesplazamientoLayout.setVerticalGroup(
            panelResultadosDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResultadosDesplazamientoLayout.createSequentialGroup()
                .addGroup(panelResultadosDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(contenedorResultadosDesplazamiento, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(contenedorResultadosDesplazamientoTrigramas, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(contenedorResultadosDesplazamientoBigramas, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelResultadosDesplazamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoMasProbablesClavesDesplazamientoR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoMasProbablesClavesDesplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelResultadosDesplazamiento.setBounds(395, 10, 370, 248);
        panelMetodoDesplazamiento.add(panelResultadosDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDescripcionDesplazamiento1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionDesplazamiento1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionDesplazamiento1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        textoDescripcionDesplazamiento1.setEnabled(false);
        textoDescripcionDesplazamiento1.setFocusable(false);

        textoDescripcionDesplazamientoT1.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionDesplazamientoT1.setColumns(20);
        textoDescripcionDesplazamientoT1.setEditable(false);
        textoDescripcionDesplazamientoT1.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionDesplazamientoT1.setLineWrap(true);
        textoDescripcionDesplazamientoT1.setRows(5);
        textoDescripcionDesplazamientoT1.setText("Cada letra del texto plano es reemplazada por la \nk-ésima letra del alfabeto que le sigue en orden. \nSiendo k la clave utilizada.");
        textoDescripcionDesplazamientoT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionDesplazamientoT1.setFocusable(false);
        textoDescripcionDesplazamientoT1.setOpaque(false);
        textoDescripcionDesplazamientoT1.setRequestFocusEnabled(false);
        textoDescripcionDesplazamiento1.setViewportView(textoDescripcionDesplazamientoT1);

        textoDescripcionDesplazamiento1.setBounds(120, 60, 255, 50);
        panelMetodoDesplazamiento.add(textoDescripcionDesplazamiento1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarDesplazamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarDesplazamiento.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarDesplazamiento.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarDesplazamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarDesplazamientoActionPerformed(evt);
            }
        });
        botonLimpiarDesplazamiento.setBounds(360, 130, 30, 30);
        panelMetodoDesplazamiento.add(botonLimpiarDesplazamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosClasicos.addTab("Desplazamiento", new javax.swing.ImageIcon(getClass().getResource("/images/clasico.png")), panelMetodoDesplazamiento); // NOI18N

        panelMetodoSustitucion.setName("sustitucion"); // NOI18N

        botonClaveSustitucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveSustitucion.setText("Generar una clave");
        botonClaveSustitucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveSustitucionActionPerformed(evt);
            }
        });
        botonClaveSustitucion.setBounds(10, 130, 340, 30);
        panelMetodoSustitucion.add(botonClaveSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoSustitucion.setText("Tipo de cifrado:");
        textoTipoCifradoSustitucion.setFocusable(false);
        textoTipoCifradoSustitucion.setInheritsPopupMenu(false);
        textoTipoCifradoSustitucion.setRequestFocusEnabled(false);
        textoTipoCifradoSustitucion.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoSustitucion.setBounds(25, 29, 90, 20);
        panelMetodoSustitucion.add(textoTipoCifradoSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoFuncionamientoSustitucion.setText("Funcionamiento:");
        textoFuncionamientoSustitucion.setFocusable(false);
        textoFuncionamientoSustitucion.setInheritsPopupMenu(false);
        textoFuncionamientoSustitucion.setRequestFocusEnabled(false);
        textoFuncionamientoSustitucion.setVerifyInputWhenFocusTarget(false);
        textoFuncionamientoSustitucion.setBounds(25, 51, 90, 58);
        panelMetodoSustitucion.add(textoFuncionamientoSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoSustitucionT.setText("Monoalfabético por sustitución (z26)");
        textoTipoCifradoSustitucionT.setFocusable(false);
        textoTipoCifradoSustitucionT.setInheritsPopupMenu(false);
        textoTipoCifradoSustitucionT.setRequestFocusEnabled(false);
        textoTipoCifradoSustitucionT.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoSustitucionT.setBounds(117, 29, 270, 20);
        panelMetodoSustitucion.add(textoTipoCifradoSustitucionT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDescripcionSustitucion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionSustitucion.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionSustitucion.setEnabled(false);
        textoDescripcionSustitucion.setFocusable(false);
        textoDescripcionSustitucion.setFont(new java.awt.Font("Tahoma", 0, 10));
        textoDescripcionSustitucion.setOpaque(false);
        textoDescripcionSustitucion.setRequestFocusEnabled(false);
        textoDescripcionSustitucion.setVerifyInputWhenFocusTarget(false);

        textoDescripcionSustitucionT.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionSustitucionT.setColumns(20);
        textoDescripcionSustitucionT.setEditable(false);
        textoDescripcionSustitucionT.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionSustitucionT.setLineWrap(true);
        textoDescripcionSustitucionT.setRows(4);
        textoDescripcionSustitucionT.setText("Al cifrar, cada letra del texto plano es reemplazada por el símbolo que ocupa la misma posición en la clave seleccionada. El paso de texto cifrado a plano es MANUAL.");
        textoDescripcionSustitucionT.setWrapStyleWord(true);
        textoDescripcionSustitucionT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionSustitucionT.setFocusable(false);
        textoDescripcionSustitucionT.setOpaque(false);
        textoDescripcionSustitucionT.setRequestFocusEnabled(false);
        textoDescripcionSustitucionT.setVerifyInputWhenFocusTarget(false);
        textoDescripcionSustitucion.setViewportView(textoDescripcionSustitucionT);

        textoDescripcionSustitucion.setBounds(117, 50, 265, 60);
        panelMetodoSustitucion.add(textoDescripcionSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMuestraSustitucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "El alfabeto original será sustituido", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelMuestraSustitucion.setFocusable(false);

        labelMuestraSustitucionDe.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelMuestraSustitucionDe.setText("De:");

        muestraSustitucionDe.setEditable(false);
        muestraSustitucionDe.setFont(new java.awt.Font("Tahoma", 0, 10));
        muestraSustitucionDe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        muestraSustitucionDe.setText("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        labelClaveSustitucion.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelClaveSustitucion.setText("a:");

        claveSustitucion.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionKeyTyped(evt);
            }
        });

        textoSustitucionDes.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoSustitucionDes.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoSustitucionDes.setEnabled(false);
        textoSustitucionDes.setFocusable(false);
        textoSustitucionDes.setFont(new java.awt.Font("Tahoma", 0, 10));
        textoSustitucionDes.setPreferredSize(new java.awt.Dimension(160, 70));

        textoSustitucionDesT.setBackground(new java.awt.Color(232, 232, 232));
        textoSustitucionDesT.setColumns(20);
        textoSustitucionDesT.setEditable(false);
        textoSustitucionDesT.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoSustitucionDesT.setLineWrap(true);
        textoSustitucionDesT.setRows(3);
        textoSustitucionDesT.setText("Ingrese la clave de acuerdo a como desee la sustitución del alfabeto.");
        textoSustitucionDesT.setWrapStyleWord(true);
        textoSustitucionDesT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoSustitucionDesT.setFocusable(false);
        textoSustitucionDesT.setOpaque(false);
        textoSustitucionDesT.setRequestFocusEnabled(false);
        textoSustitucionDesT.setVerifyInputWhenFocusTarget(false);
        textoSustitucionDes.setViewportView(textoSustitucionDesT);

        javax.swing.GroupLayout panelMuestraSustitucionLayout = new javax.swing.GroupLayout(panelMuestraSustitucion);
        panelMuestraSustitucion.setLayout(panelMuestraSustitucionLayout);
        panelMuestraSustitucionLayout.setHorizontalGroup(
            panelMuestraSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMuestraSustitucionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textoSustitucionDes, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMuestraSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(labelClaveSustitucion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelMuestraSustitucionDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMuestraSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(muestraSustitucionDe, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSustitucion, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelMuestraSustitucionLayout.setVerticalGroup(
            panelMuestraSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMuestraSustitucionLayout.createSequentialGroup()
                .addGroup(panelMuestraSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMuestraSustitucionLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(textoSustitucionDes, 0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelMuestraSustitucionLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(panelMuestraSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(muestraSustitucionDe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelMuestraSustitucionDe))
                        .addGap(6, 6, 6)
                        .addGroup(panelMuestraSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claveSustitucion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelClaveSustitucion))))
                .addGap(17, 17, 17))
        );

        panelMuestraSustitucion.setBounds(10, 168, 380, 90);
        panelMetodoSustitucion.add(panelMuestraSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionSustitucion.setBorder(null);
        panelDescripcionSustitucion.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionSustitucion.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionSustitucion.setEnabled(false);
        panelDescripcionSustitucion.setFocusable(false);
        panelDescripcionSustitucion.setOpaque(false);
        panelDescripcionSustitucion.setPreferredSize(new java.awt.Dimension(370, 110));
        panelDescripcionSustitucion.setRequestFocusEnabled(false);
        panelDescripcionSustitucion.setWheelScrollingEnabled(false);

        cajaDescripcionSustitucion.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionSustitucion.setColumns(20);
        cajaDescripcionSustitucion.setEditable(false);
        cajaDescripcionSustitucion.setRows(4);
        cajaDescripcionSustitucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionSustitucion.setEnabled(false);
        cajaDescripcionSustitucion.setFocusable(false);
        cajaDescripcionSustitucion.setOpaque(false);
        cajaDescripcionSustitucion.setRequestFocusEnabled(false);
        cajaDescripcionSustitucion.setVerifyInputWhenFocusTarget(false);
        panelDescripcionSustitucion.setViewportView(cajaDescripcionSustitucion);

        panelDescripcionSustitucion.setBounds(10, 10, 380, 110);
        panelMetodoSustitucion.add(panelDescripcionSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelResultadosSustitucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelResultadosSustitucion.setFocusable(false);

        tablaResultadosSusLetras.setAutoCreateRowSorter(true);
        tablaResultadosSusLetras.setFont(new java.awt.Font("Tahoma", 0, 10));
        tablaResultadosSusLetras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosSusLetras.setFocusable(false);
        tablaResultadosSusLetras.setRowSelectionAllowed(false);
        contenedorResultadoSusLetras.setViewportView(tablaResultadosSusLetras);

        tablaResultadosSusBigramas.setAutoCreateRowSorter(true);
        tablaResultadosSusBigramas.setFont(new java.awt.Font("Tahoma", 0, 10));
        tablaResultadosSusBigramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosSusBigramas.setFocusable(false);
        tablaResultadosSusBigramas.setRowSelectionAllowed(false);
        contenedorResultadosSusBigramas.setViewportView(tablaResultadosSusBigramas);

        tablaResultadosSusTrigramas.setAutoCreateRowSorter(true);
        tablaResultadosSusTrigramas.setFont(new java.awt.Font("Tahoma", 0, 10));
        tablaResultadosSusTrigramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosSusTrigramas.setFocusable(false);
        tablaResultadosSusTrigramas.setRowSelectionAllowed(false);
        contenedorResultadosSusTrigramas.setViewportView(tablaResultadosSusTrigramas);

        javax.swing.GroupLayout panelResultadosSustitucionLayout = new javax.swing.GroupLayout(panelResultadosSustitucion);
        panelResultadosSustitucion.setLayout(panelResultadosSustitucionLayout);
        panelResultadosSustitucionLayout.setHorizontalGroup(
            panelResultadosSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResultadosSustitucionLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(contenedorResultadoSusLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(contenedorResultadosSusBigramas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(contenedorResultadosSusTrigramas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(11, Short.MAX_VALUE))
        );
        panelResultadosSustitucionLayout.setVerticalGroup(
            panelResultadosSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResultadosSustitucionLayout.createSequentialGroup()
                .addGroup(panelResultadosSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contenedorResultadosSusTrigramas, 0, 0, Short.MAX_VALUE)
                    .addComponent(contenedorResultadosSusBigramas, 0, 0, Short.MAX_VALUE)
                    .addComponent(contenedorResultadoSusLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelResultadosSustitucion.setBounds(395, 10, 370, 113);
        panelMetodoSustitucion.add(panelResultadosSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarSustitucion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarSustitucion.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarSustitucion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarSustitucion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarSustitucionActionPerformed(evt);
            }
        });
        botonLimpiarSustitucion.setBounds(360, 130, 30, 30);
        panelMetodoSustitucion.add(botonLimpiarSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelAnalisisManualSustitucion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Análisis manual", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N

        textoMasProbablesClavesSustitucionR.setFont(new java.awt.Font("Tahoma", 1, 9));
        textoMasProbablesClavesSustitucionR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoMasProbablesClavesSustitucionR.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        textoMasProbablesClavesSustitucionR.setFocusable(false);
        textoMasProbablesClavesSustitucionR.setInheritsPopupMenu(false);

        labelSusManualA.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualA.setText("a:");
        labelSusManualA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionA.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionAKeyTyped(evt);
            }
        });

        labelSusManualB.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualB.setText("b:");
        labelSusManualB.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionB.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionBKeyTyped(evt);
            }
        });

        labelSusManualC.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualC.setText("c:");
        labelSusManualC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionC.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionCKeyTyped(evt);
            }
        });

        labelSusManualD.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualD.setText("d:");
        labelSusManualD.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionD.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionDKeyTyped(evt);
            }
        });

        labelSusManualE.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualE.setText("e:");
        labelSusManualE.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionE.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionE.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionE.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionEKeyTyped(evt);
            }
        });

        labelSusManualF.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualF.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualF.setText("f:");
        labelSusManualF.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionF.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionFKeyTyped(evt);
            }
        });

        labelSusManualG.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualG.setText("g:");
        labelSusManualG.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionG.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionG.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionG.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionGKeyTyped(evt);
            }
        });

        labelSusManualH.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualH.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualH.setText("h:");
        labelSusManualH.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionH.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionH.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionH.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionHKeyTyped(evt);
            }
        });

        labelSusManualI.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualI.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualI.setText("i:");
        labelSusManualI.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionI.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionI.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionIKeyTyped(evt);
            }
        });

        labelSusManualJ.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualJ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualJ.setText("j:");
        labelSusManualJ.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionJ.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionJ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionJ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionJKeyTyped(evt);
            }
        });

        labelSusManualK.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualK.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualK.setText("k:");
        labelSusManualK.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionK.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionK.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionKKeyTyped(evt);
            }
        });

        labelSusManualL.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualL.setText("l:");
        labelSusManualL.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionL.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionLKeyTyped(evt);
            }
        });

        labelSusManualM.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        labelSusManualM.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualM.setText("m:");
        labelSusManualM.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionM.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionM.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionM.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionMKeyTyped(evt);
            }
        });

        labelSusManualN.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualN.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualN.setText("n:");
        labelSusManualN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionN.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionNKeyTyped(evt);
            }
        });

        labelSusManualO.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualO.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualO.setText("o:");
        labelSusManualO.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionO.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionO.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionO.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionOKeyTyped(evt);
            }
        });

        labelSusManualP.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualP.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualP.setText("p:");
        labelSusManualP.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionP.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionP.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionP.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionPKeyTyped(evt);
            }
        });

        labelSusManualQ.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualQ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualQ.setText("q:");
        labelSusManualQ.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionQ.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionQ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionQ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionQKeyTyped(evt);
            }
        });

        labelSusManualR.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualR.setText("r:");
        labelSusManualR.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionR.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionR.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionR.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionRKeyTyped(evt);
            }
        });

        labelSusManualS.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualS.setText("s:");
        labelSusManualS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionS.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionSKeyTyped(evt);
            }
        });

        labelSusManualT.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualT.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualT.setText("t:");
        labelSusManualT.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionT.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionTKeyTyped(evt);
            }
        });

        labelSusManualU.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualU.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualU.setText("u:");
        labelSusManualU.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionU.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionU.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionU.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionUKeyTyped(evt);
            }
        });

        labelSusManualV.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualV.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualV.setText("v:");
        labelSusManualV.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionV.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionV.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionV.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionVKeyTyped(evt);
            }
        });

        labelSusManualW.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualW.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualW.setText("w:");
        labelSusManualW.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionW.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionW.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionW.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionWKeyTyped(evt);
            }
        });

        labelSusManualX.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualX.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualX.setText("x:");
        labelSusManualX.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionX.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionX.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionX.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionXKeyTyped(evt);
            }
        });

        labelSusManualY.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualY.setText("y:");
        labelSusManualY.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionY.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionY.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionY.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionYKeyTyped(evt);
            }
        });

        labelSusManualZ.setFont(new java.awt.Font("DejaVu Sans", 0, 12));
        labelSusManualZ.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSusManualZ.setText("z:");
        labelSusManualZ.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        claveSustitucionZ.setFont(new java.awt.Font("Tahoma", 0, 10));
        claveSustitucionZ.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSustitucionZ.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSustitucionZKeyTyped(evt);
            }
        });

        botonLimpiarSustitucionManual.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarSustitucionManual.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarSustitucionManual.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarSustitucionManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarSustitucionManualActionPerformed(evt);
            }
        });

        labelClaveActualSust.setText("Clave actual:");

        javax.swing.GroupLayout panelAnalisisManualSustitucionLayout = new javax.swing.GroupLayout(panelAnalisisManualSustitucion);
        panelAnalisisManualSustitucion.setLayout(panelAnalisisManualSustitucionLayout);
        panelAnalisisManualSustitucionLayout.setHorizontalGroup(
            panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSusManualJ, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSusManualS, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSusManualA, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(claveSustitucionS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSustitucionJ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSustitucionA, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSusManualT, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSusManualK, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSusManualB, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(claveSustitucionB, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSustitucionK, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSustitucionT, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelSusManualC, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSusManualL, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelSusManualU, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(claveSustitucionC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSustitucionL, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSustitucionU, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualM, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(2, 2, 2)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveSustitucionM, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionD, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveSustitucionN, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionE, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveSustitucionO, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveSustitucionP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionG, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveSustitucionQ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionH, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(4, 4, 4)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveSustitucionR, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionI, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                        .addComponent(labelSusManualV, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(claveSustitucionV, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(labelSusManualW, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(claveSustitucionW, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(labelSusManualX, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(claveSustitucionX, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(4, 4, 4)
                        .addComponent(labelSusManualY, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(claveSustitucionY, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(labelSusManualZ, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(claveSustitucionZ, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(labelClaveActualSust, javax.swing.GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
                .addGap(276, 276, 276))
            .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(textoMasProbablesClavesSustitucionR, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonLimpiarSustitucionManual, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        panelAnalisisManualSustitucionLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {labelSusManualD, labelSusManualM});

        panelAnalisisManualSustitucionLayout.setVerticalGroup(
            panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualB)
                            .addComponent(claveSustitucionB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualC)
                            .addComponent(claveSustitucionC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualD)
                            .addComponent(claveSustitucionD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualE)
                            .addComponent(claveSustitucionE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualF)
                            .addComponent(claveSustitucionF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualG)
                            .addComponent(claveSustitucionG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualH)
                            .addComponent(claveSustitucionH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualI)
                            .addComponent(claveSustitucionI, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualA))
                        .addGap(3, 3, 3)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSusManualJ)
                            .addComponent(claveSustitucionJ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualK)
                            .addComponent(claveSustitucionK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualL)
                            .addComponent(claveSustitucionL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualM)
                            .addComponent(claveSustitucionM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualN)
                            .addComponent(claveSustitucionN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualO)
                            .addComponent(claveSustitucionO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualP)
                            .addComponent(claveSustitucionP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualQ)
                            .addComponent(claveSustitucionQ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualR)
                            .addComponent(claveSustitucionR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(3, 3, 3)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveSustitucionS, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualT, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveSustitucionT, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualU, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveSustitucionU, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualV, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveSustitucionV, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveSustitucionW, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualX, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveSustitucionX, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualY, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveSustitucionY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSusManualZ, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveSustitucionZ, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelAnalisisManualSustitucionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(botonLimpiarSustitucionManual, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textoMasProbablesClavesSustitucionR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(labelClaveActualSust, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAnalisisManualSustitucionLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(labelSusManualS)))
                .addContainerGap())
        );

        panelAnalisisManualSustitucionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {claveSustitucionA, labelSusManualA});

        panelAnalisisManualSustitucionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {claveSustitucionB, claveSustitucionC, claveSustitucionD, claveSustitucionE, claveSustitucionF, claveSustitucionG, claveSustitucionH, claveSustitucionI, labelSusManualB, labelSusManualC, labelSusManualD, labelSusManualE, labelSusManualF, labelSusManualG, labelSusManualH, labelSusManualI});

        panelAnalisisManualSustitucionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {claveSustitucionJ, claveSustitucionK, claveSustitucionL, claveSustitucionM, claveSustitucionN, claveSustitucionO, claveSustitucionP, claveSustitucionQ, claveSustitucionR, labelSusManualJ, labelSusManualK, labelSusManualL, labelSusManualM, labelSusManualN, labelSusManualO, labelSusManualP, labelSusManualQ, labelSusManualR});

        panelAnalisisManualSustitucionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {claveSustitucionS, claveSustitucionT, claveSustitucionU, claveSustitucionV, claveSustitucionW, claveSustitucionX, claveSustitucionY, claveSustitucionZ, labelSusManualS, labelSusManualT, labelSusManualU, labelSusManualV, labelSusManualW, labelSusManualX, labelSusManualY, labelSusManualZ});

        panelAnalisisManualSustitucion.setBounds(395, 130, 370, 128);
        panelMetodoSustitucion.add(panelAnalisisManualSustitucion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosClasicos.addTab("Sustitución", new javax.swing.ImageIcon(getClass().getResource("/images/clasico.png")), panelMetodoSustitucion); // NOI18N

        panelMetodoAffine.setName("affine"); // NOI18N

        botonClaveAffine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveAffine.setText("Generar una clave");
        botonClaveAffine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveAffineActionPerformed(evt);
            }
        });
        botonClaveAffine.setBounds(10, 130, 340, 30);
        panelMetodoAffine.add(botonClaveAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoAffine.setText("Tipo de cifrado:");
        textoTipoCifradoAffine.setFocusable(false);
        textoTipoCifradoAffine.setInheritsPopupMenu(false);
        textoTipoCifradoAffine.setRequestFocusEnabled(false);
        textoTipoCifradoAffine.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoAffine.setBounds(25, 29, 90, 20);
        panelMetodoAffine.add(textoTipoCifradoAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoFuncionamientoAffine.setText("Funcionamiento:");
        textoFuncionamientoAffine.setFocusable(false);
        textoFuncionamientoAffine.setInheritsPopupMenu(false);
        textoFuncionamientoAffine.setRequestFocusEnabled(false);
        textoFuncionamientoAffine.setVerifyInputWhenFocusTarget(false);
        textoFuncionamientoAffine.setBounds(25, 51, 90, 58);
        panelMetodoAffine.add(textoFuncionamientoAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoAffineT.setText("Monoalfabético por sustitución (z26)");
        textoTipoCifradoAffineT.setFocusable(false);
        textoTipoCifradoAffineT.setInheritsPopupMenu(false);
        textoTipoCifradoAffineT.setRequestFocusEnabled(false);
        textoTipoCifradoAffineT.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoAffineT.setBounds(117, 29, 268, 20);
        panelMetodoAffine.add(textoTipoCifradoAffineT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDescripcionAffine.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionAffine.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionAffine.setEnabled(false);
        textoDescripcionAffine.setFocusable(false);

        textoDescripcionAffineT.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionAffineT.setColumns(20);
        textoDescripcionAffineT.setEditable(false);
        textoDescripcionAffineT.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionAffineT.setLineWrap(true);
        textoDescripcionAffineT.setRows(4);
        textoDescripcionAffineT.setText("Al cifrar, cada letra del texto plano es desplazada según la fórmula e(x) = (Ax+B) mod 26. El paso de texto cifrado a texto plano es realizado por CRIPTOANáLISIS.");
        textoDescripcionAffineT.setWrapStyleWord(true);
        textoDescripcionAffineT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionAffineT.setFocusable(false);
        textoDescripcionAffineT.setOpaque(false);
        textoDescripcionAffineT.setRequestFocusEnabled(false);
        textoDescripcionAffineT.setVerifyInputWhenFocusTarget(false);
        textoDescripcionAffine.setViewportView(textoDescripcionAffineT);

        textoDescripcionAffine.setBounds(117, 50, 265, 61);
        panelMetodoAffine.add(textoDescripcionAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveAffine.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave como", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveAffine.setFocusable(false);

        claveAffineA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveAffineA.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveAffineAKeyTyped(evt);
            }
        });

        claveAffineB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveAffineB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveAffineBKeyTyped(evt);
            }
        });

        textoAffine1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoAffine1.setText("(");
        textoAffine1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        textoAffine2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoAffine2.setText(",");
        textoAffine2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        textoAffine3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoAffine3.setText(")");
        textoAffine3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        textoAffineClave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoAffineClave.setText("Una pareja (A.B) donde A debe ser primo relativo de 26");
        textoAffineClave.setFocusable(false);
        textoAffineClave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        textoAffineClave.setRequestFocusEnabled(false);
        textoAffineClave.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panelClaveAffineLayout = new javax.swing.GroupLayout(panelClaveAffine);
        panelClaveAffine.setLayout(panelClaveAffineLayout);
        panelClaveAffineLayout.setHorizontalGroup(
            panelClaveAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveAffineLayout.createSequentialGroup()
                .addGroup(panelClaveAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClaveAffineLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textoAffineClave, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                    .addGroup(panelClaveAffineLayout.createSequentialGroup()
                        .addGap(120, 120, 120)
                        .addComponent(textoAffine1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(claveAffineA, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoAffine2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(claveAffineB, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoAffine3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelClaveAffineLayout.setVerticalGroup(
            panelClaveAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveAffineLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoAffineClave)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelClaveAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(claveAffineA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoAffine1)
                    .addComponent(textoAffine2)
                    .addComponent(claveAffineB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoAffine3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveAffine.setBounds(10, 168, 380, 90);
        panelMetodoAffine.add(panelClaveAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionAffine.setBorder(null);
        panelDescripcionAffine.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionAffine.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionAffine.setEnabled(false);
        panelDescripcionAffine.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionAffine.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionAffine.setColumns(20);
        cajaDescripcionAffine.setEditable(false);
        cajaDescripcionAffine.setRows(4);
        cajaDescripcionAffine.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionAffine.setEnabled(false);
        cajaDescripcionAffine.setFocusable(false);
        cajaDescripcionAffine.setOpaque(false);
        cajaDescripcionAffine.setRequestFocusEnabled(false);
        cajaDescripcionAffine.setVerifyInputWhenFocusTarget(false);
        panelDescripcionAffine.setViewportView(cajaDescripcionAffine);

        panelDescripcionAffine.setBounds(10, 10, 380, 110);
        panelMetodoAffine.add(panelDescripcionAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelResultadosAffine.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelResultadosAffine.setFocusable(false);

        tablaResultadosAffineLetras.setAutoCreateRowSorter(true);
        tablaResultadosAffineLetras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosAffineLetras.setFocusable(false);
        tablaResultadosAffineLetras.setRowSelectionAllowed(false);
        contenedorResultadoAffineLetras.setViewportView(tablaResultadosAffineLetras);

        tablaResultadosAffineBigramas.setAutoCreateRowSorter(true);
        tablaResultadosAffineBigramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosAffineBigramas.setFocusable(false);
        tablaResultadosAffineBigramas.setRowSelectionAllowed(false);
        contenedorResultadosAffineBigramas.setViewportView(tablaResultadosAffineBigramas);

        tablaResultadosAffineTrigramas.setAutoCreateRowSorter(true);
        tablaResultadosAffineTrigramas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null}
            },
            new String [] {
                "Resultados"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaResultadosAffineTrigramas.setFocusable(false);
        tablaResultadosAffineTrigramas.setRowSelectionAllowed(false);
        contenedorResultadosAffineTrigramas.setViewportView(tablaResultadosAffineTrigramas);

        textoMasProbablesClavesAffineR.setFont(new java.awt.Font("Tahoma", 1, 11));
        textoMasProbablesClavesAffineR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoMasProbablesClavesAffineR.setFocusable(false);
        textoMasProbablesClavesAffineR.setInheritsPopupMenu(false);

        textoMasProbablesClavesAffine.setText("Según el análisis de frecuencia las claves mas probables son:");
        textoMasProbablesClavesAffine.setFocusable(false);
        textoMasProbablesClavesAffine.setInheritsPopupMenu(false);
        textoMasProbablesClavesAffine.setRequestFocusEnabled(false);
        textoMasProbablesClavesAffine.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panelResultadosAffineLayout = new javax.swing.GroupLayout(panelResultadosAffine);
        panelResultadosAffine.setLayout(panelResultadosAffineLayout);
        panelResultadosAffineLayout.setHorizontalGroup(
            panelResultadosAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResultadosAffineLayout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addGroup(panelResultadosAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResultadosAffineLayout.createSequentialGroup()
                        .addComponent(contenedorResultadoAffineLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(contenedorResultadosAffineBigramas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(contenedorResultadosAffineTrigramas, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(11, Short.MAX_VALUE))
                    .addGroup(panelResultadosAffineLayout.createSequentialGroup()
                        .addGroup(panelResultadosAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoMasProbablesClavesAffine, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                            .addGroup(panelResultadosAffineLayout.createSequentialGroup()
                                .addComponent(textoMasProbablesClavesAffineR, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)))
                        .addGap(7, 7, 7))))
        );
        panelResultadosAffineLayout.setVerticalGroup(
            panelResultadosAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelResultadosAffineLayout.createSequentialGroup()
                .addGroup(panelResultadosAffineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(contenedorResultadosAffineTrigramas, 0, 0, Short.MAX_VALUE)
                    .addComponent(contenedorResultadosAffineBigramas, 0, 0, Short.MAX_VALUE)
                    .addComponent(contenedorResultadoAffineLetras, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoMasProbablesClavesAffine, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(textoMasProbablesClavesAffineR, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelResultadosAffine.setBounds(395, 10, 370, 248);
        panelMetodoAffine.add(panelResultadosAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarAffine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarAffine.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarAffine.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarAffine.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarAffineActionPerformed(evt);
            }
        });
        botonLimpiarAffine.setBounds(360, 130, 30, 30);
        panelMetodoAffine.add(botonLimpiarAffine, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosClasicos.addTab("Affine", new javax.swing.ImageIcon(getClass().getResource("/images/clasico.png")), panelMetodoAffine); // NOI18N

        panelMetodoVigenere.setName("vigenere"); // NOI18N

        botonClaveVigenere.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveVigenere.setText("Generar una clave");
        botonClaveVigenere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveVigenereActionPerformed(evt);
            }
        });
        botonClaveVigenere.setBounds(10, 130, 350, 30);
        panelMetodoVigenere.add(botonClaveVigenere, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoVigenere.setText("Tipo de cifrado:");
        textoTipoCifradoVigenere.setFocusable(false);
        textoTipoCifradoVigenere.setInheritsPopupMenu(false);
        textoTipoCifradoVigenere.setRequestFocusEnabled(false);
        textoTipoCifradoVigenere.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoVigenere.setBounds(25, 29, 90, 20);
        panelMetodoVigenere.add(textoTipoCifradoVigenere, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoFuncionamientoVigenere.setText("Funcionamiento:");
        textoFuncionamientoVigenere.setFocusable(false);
        textoFuncionamientoVigenere.setInheritsPopupMenu(false);
        textoFuncionamientoVigenere.setRequestFocusEnabled(false);
        textoFuncionamientoVigenere.setVerifyInputWhenFocusTarget(false);
        textoFuncionamientoVigenere.setBounds(25, 51, 90, 58);
        panelMetodoVigenere.add(textoFuncionamientoVigenere, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoVigenereT.setText("Polialfabético por sustitución (z26)");
        textoTipoCifradoVigenereT.setFocusable(false);
        textoTipoCifradoVigenereT.setInheritsPopupMenu(false);
        textoTipoCifradoVigenereT.setRequestFocusEnabled(false);
        textoTipoCifradoVigenereT.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoVigenereT.setBounds(117, 29, 268, 20);
        panelMetodoVigenere.add(textoTipoCifradoVigenereT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDescripcionVigenere.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionVigenere.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionVigenere.setEnabled(false);
        textoDescripcionVigenere.setFocusable(false);
        textoDescripcionVigenere.setFont(new java.awt.Font("Tahoma", 0, 10));

        textoDescripcionVigenereT.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionVigenereT.setColumns(20);
        textoDescripcionVigenereT.setEditable(false);
        textoDescripcionVigenereT.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionVigenereT.setLineWrap(true);
        textoDescripcionVigenereT.setRows(4);
        textoDescripcionVigenereT.setText("Al cifrar, se sitúa la clave debajo del texto plano de manera repetitiva hasta cubrir su longitud, luego se suma cada columna. El paso de texto cifrado a plano se realiza por CRIPTOANáLISIS.");
        textoDescripcionVigenereT.setWrapStyleWord(true);
        textoDescripcionVigenereT.setAutoscrolls(false);
        textoDescripcionVigenereT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionVigenereT.setFocusable(false);
        textoDescripcionVigenereT.setOpaque(false);
        textoDescripcionVigenereT.setRequestFocusEnabled(false);
        textoDescripcionVigenereT.setVerifyInputWhenFocusTarget(false);
        textoDescripcionVigenere.setViewportView(textoDescripcionVigenereT);

        textoDescripcionVigenere.setBounds(117, 50, 270, 61);
        panelMetodoVigenere.add(textoDescripcionVigenere, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelTipoClaveDesplazamiento1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelTipoClaveDesplazamiento1.setFocusable(false);

        claveVigenere.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveVigenere.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveVigenereKeyTyped(evt);
            }
        });

        textoDescripcion10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcion10.setText("Máximo 8 caracteres y mínimo 3 caracteres");
        textoDescripcion10.setFocusable(false);
        textoDescripcion10.setInheritsPopupMenu(false);
        textoDescripcion10.setRequestFocusEnabled(false);
        textoDescripcion10.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout panelTipoClaveDesplazamiento1Layout = new javax.swing.GroupLayout(panelTipoClaveDesplazamiento1);
        panelTipoClaveDesplazamiento1.setLayout(panelTipoClaveDesplazamiento1Layout);
        panelTipoClaveDesplazamiento1Layout.setHorizontalGroup(
            panelTipoClaveDesplazamiento1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoClaveDesplazamiento1Layout.createSequentialGroup()
                .addGroup(panelTipoClaveDesplazamiento1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTipoClaveDesplazamiento1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textoDescripcion10, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE))
                    .addGroup(panelTipoClaveDesplazamiento1Layout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(claveVigenere, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTipoClaveDesplazamiento1Layout.setVerticalGroup(
            panelTipoClaveDesplazamiento1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTipoClaveDesplazamiento1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcion10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveVigenere, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTipoClaveDesplazamiento1.setBounds(10, 168, 390, 90);
        panelMetodoVigenere.add(panelTipoClaveDesplazamiento1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionVigenere.setBorder(null);
        panelDescripcionVigenere.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionVigenere.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionVigenere.setEnabled(false);
        panelDescripcionVigenere.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionVigenere.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionVigenere.setColumns(20);
        cajaDescripcionVigenere.setEditable(false);
        cajaDescripcionVigenere.setRows(4);
        cajaDescripcionVigenere.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionVigenere.setEnabled(false);
        cajaDescripcionVigenere.setFocusable(false);
        cajaDescripcionVigenere.setOpaque(false);
        cajaDescripcionVigenere.setRequestFocusEnabled(false);
        cajaDescripcionVigenere.setVerifyInputWhenFocusTarget(false);
        panelDescripcionVigenere.setViewportView(cajaDescripcionVigenere);

        panelDescripcionVigenere.setBounds(10, 10, 390, 110);
        panelMetodoVigenere.add(panelDescripcionVigenere, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelResultadosVigenere.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Resultados", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelResultadosVigenere.setFocusable(false);

        textoResultadoVigenere.setFont(new java.awt.Font("Tahoma", 0, 12));
        textoResultadoVigenere.setText("El texto fue cifrado con la clave");
        textoResultadoVigenere.setFocusable(false);
        textoResultadoVigenere.setRequestFocusEnabled(false);
        textoResultadoVigenere.setVerifyInputWhenFocusTarget(false);

        textoResultadoVigenereClave.setFont(new java.awt.Font("Tahoma", 0, 18));
        textoResultadoVigenereClave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panelResultadosVigenereLayout = new javax.swing.GroupLayout(panelResultadosVigenere);
        panelResultadosVigenere.setLayout(panelResultadosVigenereLayout);
        panelResultadosVigenereLayout.setHorizontalGroup(
            panelResultadosVigenereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResultadosVigenereLayout.createSequentialGroup()
                .addGroup(panelResultadosVigenereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelResultadosVigenereLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(textoResultadoVigenereClave, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelResultadosVigenereLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(textoResultadoVigenere)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        panelResultadosVigenereLayout.setVerticalGroup(
            panelResultadosVigenereLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelResultadosVigenereLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(textoResultadoVigenere)
                .addGap(47, 47, 47)
                .addComponent(textoResultadoVigenereClave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        panelResultadosVigenere.setBounds(405, 10, 360, 248);
        panelMetodoVigenere.add(panelResultadosVigenere, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarVigenere.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarVigenere.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarVigenere.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarVigenere.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarVigenereActionPerformed(evt);
            }
        });
        botonLimpiarVigenere.setBounds(370, 130, 30, 30);
        panelMetodoVigenere.add(botonLimpiarVigenere, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosClasicos.addTab("Vigenere", new javax.swing.ImageIcon(getClass().getResource("/images/clasico.png")), panelMetodoVigenere); // NOI18N

        panelMetodoHill.setName("hill"); // NOI18N

        infoHill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoHill.setBounds(40, 200, 23, 23);
        panelMetodoHill.add(infoHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarHill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarHill.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarHill.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarHill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarHillActionPerformed(evt);
            }
        });
        botonLimpiarHill.setBounds(370, 130, 30, 30);
        panelMetodoHill.add(botonLimpiarHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveHill.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveHill.setText("Generar una clave");
        botonClaveHill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveHillActionPerformed(evt);
            }
        });
        botonClaveHill.setBounds(10, 130, 350, 30);
        panelMetodoHill.add(botonClaveHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoHill.setText("Tipo de cifrado:");
        textoTipoCifradoHill.setFocusable(false);
        textoTipoCifradoHill.setInheritsPopupMenu(false);
        textoTipoCifradoHill.setRequestFocusEnabled(false);
        textoTipoCifradoHill.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoHill.setBounds(25, 29, 90, 20);
        panelMetodoHill.add(textoTipoCifradoHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoFuncionamientoHill.setText("Funcionamiento:");
        textoFuncionamientoHill.setFocusable(false);
        textoFuncionamientoHill.setInheritsPopupMenu(false);
        textoFuncionamientoHill.setRequestFocusEnabled(false);
        textoFuncionamientoHill.setVerifyInputWhenFocusTarget(false);
        textoFuncionamientoHill.setBounds(25, 51, 90, 58);
        panelMetodoHill.add(textoFuncionamientoHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoHillT.setText("Polialfabético por sustitución (z26)");
        textoTipoCifradoHillT.setFocusable(false);
        textoTipoCifradoHillT.setInheritsPopupMenu(false);
        textoTipoCifradoHillT.setRequestFocusEnabled(false);
        textoTipoCifradoHillT.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoHillT.setBounds(117, 29, 268, 20);
        panelMetodoHill.add(textoTipoCifradoHillT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDescripcionHill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionHill.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionHill.setEnabled(false);
        textoDescripcionHill.setFocusable(false);
        textoDescripcionHill.setFont(new java.awt.Font("Tahoma", 0, 10));

        textoDescripcionHillT.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionHillT.setColumns(20);
        textoDescripcionHillT.setEditable(false);
        textoDescripcionHillT.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionHillT.setLineWrap(true);
        textoDescripcionHillT.setRows(4);
        textoDescripcionHillT.setText("Al cifrar, el texto plano es dividido en vectores de 1 por n, en donde n es el orden de la matriz clave, luego cada vector es multiplicado por la clave. El paso de texto cifrado a plano NO está disponible.");
        textoDescripcionHillT.setWrapStyleWord(true);
        textoDescripcionHillT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionHillT.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textoDescripcionHillT.setFocusable(false);
        textoDescripcionHillT.setOpaque(false);
        textoDescripcionHillT.setRequestFocusEnabled(false);
        textoDescripcionHillT.setVerifyInputWhenFocusTarget(false);
        textoDescripcionHill.setViewportView(textoDescripcionHillT);

        textoDescripcionHill.setBounds(117, 50, 270, 61);
        panelMetodoHill.add(textoDescripcionHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoescripcion6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoescripcion6.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoescripcion6.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        textoescripcion6.setEnabled(false);
        textoescripcion6.setFocusable(false);

        textoInfoHill.setBackground(new java.awt.Color(232, 232, 232));
        textoInfoHill.setColumns(20);
        textoInfoHill.setEditable(false);
        textoInfoHill.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoInfoHill.setLineWrap(true);
        textoInfoHill.setRows(5);
        textoInfoHill.setText("La clave debe ser una matriz invertible y el MCD de su determinante y 26 debe ser 1");
        textoInfoHill.setWrapStyleWord(true);
        textoInfoHill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoInfoHill.setFocusable(false);
        textoInfoHill.setOpaque(false);
        textoInfoHill.setRequestFocusEnabled(false);
        textoInfoHill.setVerifyInputWhenFocusTarget(false);
        textoescripcion6.setViewportView(textoInfoHill);

        textoescripcion6.setBounds(80, 200, 280, 30);
        panelMetodoHill.add(textoescripcion6, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionHillClave.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        panelDescripcionHillClave.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionHillClave.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionHillClave.setEnabled(false);
        panelDescripcionHillClave.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionHillClave.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionHillClave.setColumns(20);
        cajaDescripcionHillClave.setRows(5);
        cajaDescripcionHillClave.setBorder(null);
        cajaDescripcionHillClave.setEnabled(false);
        cajaDescripcionHillClave.setFocusable(false);
        cajaDescripcionHillClave.setOpaque(false);
        cajaDescripcionHillClave.setRequestFocusEnabled(false);
        panelDescripcionHillClave.setViewportView(cajaDescripcionHillClave);

        panelDescripcionHillClave.setBounds(10, 176, 390, 80);
        panelMetodoHill.add(panelDescripcionHillClave, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveHill.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveHill.setFocusable(false);

        claveHill1.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill1.setNextFocusableComponent(claveHill2);
        claveHill1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill1KeyTyped(evt);
            }
        });

        claveHill2.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill2.setNextFocusableComponent(claveHill3);
        claveHill2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill2KeyTyped(evt);
            }
        });

        claveHill3.setEditable(false);
        claveHill3.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill3.setNextFocusableComponent(claveHill4);
        claveHill3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill3KeyTyped(evt);
            }
        });

        claveHill4.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill4.setNextFocusableComponent(claveHill5);
        claveHill4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill4KeyTyped(evt);
            }
        });

        claveHill5.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill5.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill5.setNextFocusableComponent(claveHill6);
        claveHill5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill5KeyTyped(evt);
            }
        });

        claveHill6.setEditable(false);
        claveHill6.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill6.setNextFocusableComponent(claveHill7);
        claveHill6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill6KeyTyped(evt);
            }
        });

        claveHill7.setEditable(false);
        claveHill7.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill7.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill7.setNextFocusableComponent(claveHill8);
        claveHill7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill7KeyTyped(evt);
            }
        });

        claveHill8.setEditable(false);
        claveHill8.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill8.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill8.setNextFocusableComponent(claveHill9);
        claveHill8.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill8KeyTyped(evt);
            }
        });

        claveHill9.setEditable(false);
        claveHill9.setFont(new java.awt.Font("Tahoma", 0, 14));
        claveHill9.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveHill9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveHill9KeyTyped(evt);
            }
        });

        tipoClaveHill.add(tipoClaveHill2);
        tipoClaveHill2.setSelected(true);
        tipoClaveHill2.setText("2 x 2");
        tipoClaveHill2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveHill2ActionPerformed(evt);
            }
        });

        tipoClaveHill.add(tipoClaveHill3);
        tipoClaveHill3.setText("3 x 3");
        tipoClaveHill3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveHill3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelClaveHillLayout = new javax.swing.GroupLayout(panelClaveHill);
        panelClaveHill.setLayout(panelClaveHillLayout);
        panelClaveHillLayout.setHorizontalGroup(
            panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveHillLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tipoClaveHill3)
                    .addComponent(tipoClaveHill2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelClaveHillLayout.createSequentialGroup()
                        .addComponent(claveHill7, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(claveHill8, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(claveHill9, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClaveHillLayout.createSequentialGroup()
                        .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveHill1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill4, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(claveHill2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill5, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveHill6, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(55, 55, 55))
        );

        panelClaveHillLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {claveHill4, claveHill5, claveHill6});

        panelClaveHillLayout.setVerticalGroup(
            panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveHillLayout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClaveHillLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(tipoClaveHill2)
                        .addGap(18, 18, 18)
                        .addComponent(tipoClaveHill3))
                    .addGroup(panelClaveHillLayout.createSequentialGroup()
                        .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claveHill3, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill2, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(claveHill6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(panelClaveHillLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(claveHill9, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill8, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(claveHill7, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        panelClaveHill.setBounds(405, 10, 360, 248);
        panelMetodoHill.add(panelClaveHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionHill.setBorder(null);
        panelDescripcionHill.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionHill.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionHill.setEnabled(false);
        panelDescripcionHill.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionHill.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionHill.setColumns(20);
        cajaDescripcionHill.setEditable(false);
        cajaDescripcionHill.setRows(4);
        cajaDescripcionHill.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionHill.setEnabled(false);
        cajaDescripcionHill.setFocusable(false);
        cajaDescripcionHill.setOpaque(false);
        cajaDescripcionHill.setRequestFocusEnabled(false);
        cajaDescripcionHill.setVerifyInputWhenFocusTarget(false);
        panelDescripcionHill.setViewportView(cajaDescripcionHill);

        panelDescripcionHill.setBounds(10, 10, 390, 110);
        panelMetodoHill.add(panelDescripcionHill, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosClasicos.addTab("Hill", new javax.swing.ImageIcon(getClass().getResource("/images/clasico.png")), panelMetodoHill); // NOI18N

        panelMetodoPermutacion.setName("permutacion"); // NOI18N
        panelMetodoPermutacion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelMetodoPermutacionFocusGained(evt);
            }
        });

        botonLimpiarPermutacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarPermutacion.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarPermutacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarPermutacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarPermutacionActionPerformed(evt);
            }
        });
        botonLimpiarPermutacion.setBounds(730, 125, 30, 30);
        panelMetodoPermutacion.add(botonLimpiarPermutacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClavePermutacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClavePermutacion.setText("Generar una clave");
        botonClavePermutacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClavePermutacionActionPerformed(evt);
            }
        });
        botonClavePermutacion.setBounds(10, 125, 710, 30);
        panelMetodoPermutacion.add(botonClavePermutacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoPermutacion.setText("Tipo de cifrado:");
        textoTipoCifradoPermutacion.setFocusable(false);
        textoTipoCifradoPermutacion.setInheritsPopupMenu(false);
        textoTipoCifradoPermutacion.setRequestFocusEnabled(false);
        textoTipoCifradoPermutacion.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoPermutacion.setBounds(120, 35, 88, 20);
        panelMetodoPermutacion.add(textoTipoCifradoPermutacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoFuncionamientoPermutacion.setText("Funcionamiento:");
        textoFuncionamientoPermutacion.setFocusable(false);
        textoFuncionamientoPermutacion.setInheritsPopupMenu(false);
        textoFuncionamientoPermutacion.setRequestFocusEnabled(false);
        textoFuncionamientoPermutacion.setVerifyInputWhenFocusTarget(false);
        textoFuncionamientoPermutacion.setBounds(120, 60, 92, 45);
        panelMetodoPermutacion.add(textoFuncionamientoPermutacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoTipoCifradoPermutacionT.setText("Transposición / Permutación (z26)");
        textoTipoCifradoPermutacionT.setFocusable(false);
        textoTipoCifradoPermutacionT.setInheritsPopupMenu(false);
        textoTipoCifradoPermutacionT.setRequestFocusEnabled(false);
        textoTipoCifradoPermutacionT.setVerifyInputWhenFocusTarget(false);
        textoTipoCifradoPermutacionT.setBounds(220, 35, 260, 20);
        panelMetodoPermutacion.add(textoTipoCifradoPermutacionT, javax.swing.JLayeredPane.DEFAULT_LAYER);

        textoDescripcionPermutacion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionPermutacion.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionPermutacion.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        textoDescripcionPermutacion.setEnabled(false);
        textoDescripcionPermutacion.setFocusable(false);

        textoDescripcionPermutacionT.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionPermutacionT.setColumns(20);
        textoDescripcionPermutacionT.setEditable(false);
        textoDescripcionPermutacionT.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionPermutacionT.setLineWrap(true);
        textoDescripcionPermutacionT.setRows(4);
        textoDescripcionPermutacionT.setText("Al cifrar, el texto plano es separado en bloques del tamaño de la clave, luego cada bloque es reordenado según las posiciones que se indiquen en la clave. El paso de texto cifrado a plano NO está disponible.");
        textoDescripcionPermutacionT.setWrapStyleWord(true);
        textoDescripcionPermutacionT.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionPermutacionT.setFocusable(false);
        textoDescripcionPermutacionT.setOpaque(false);
        textoDescripcionPermutacionT.setRequestFocusEnabled(false);
        textoDescripcionPermutacionT.setVerifyInputWhenFocusTarget(false);
        textoDescripcionPermutacion.setViewportView(textoDescripcionPermutacionT);

        textoDescripcionPermutacion.setBounds(220, 60, 430, 45);
        panelMetodoPermutacion.add(textoDescripcionPermutacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionPermutacion.setBorder(null);
        panelDescripcionPermutacion.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionPermutacion.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionPermutacion.setEnabled(false);
        panelDescripcionPermutacion.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionPermutacion.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionPermutacion.setColumns(20);
        cajaDescripcionPermutacion.setEditable(false);
        cajaDescripcionPermutacion.setRows(4);
        cajaDescripcionPermutacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionPermutacion.setEnabled(false);
        cajaDescripcionPermutacion.setFocusable(false);
        cajaDescripcionPermutacion.setOpaque(false);
        cajaDescripcionPermutacion.setRequestFocusEnabled(false);
        cajaDescripcionPermutacion.setVerifyInputWhenFocusTarget(false);
        panelDescripcionPermutacion.setViewportView(cajaDescripcionPermutacion);

        panelDescripcionPermutacion.setBounds(10, 10, 750, 103);
        panelMetodoPermutacion.add(panelDescripcionPermutacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMuestraPermutacion.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese la clave para reordenar cada bloque del texto plano", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelMuestraPermutacion.setFocusable(false);

        clavePermutacion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        clavePermutacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavePermutacionActionPerformed(evt);
            }
        });
        clavePermutacion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                clavePermutacionKeyTyped(evt);
            }
        });

        clavePermutacionAlternativa.setText("Usar doble cifrado y permutación por columnas");
        clavePermutacionAlternativa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        clavePermutacionAlternativa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavePermutacionAlternativaActionPerformed(evt);
            }
        });

        textoDescripcionPermutacion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionPermutacion1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        textoDescripcionPermutacion1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        textoDescripcionPermutacion1.setEnabled(false);
        textoDescripcionPermutacion1.setFocusable(false);

        textoDescripcionPermutacionT1.setBackground(new java.awt.Color(232, 232, 232));
        textoDescripcionPermutacionT1.setColumns(20);
        textoDescripcionPermutacionT1.setEditable(false);
        textoDescripcionPermutacionT1.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDescripcionPermutacionT1.setLineWrap(true);
        textoDescripcionPermutacionT1.setRows(5);
        textoDescripcionPermutacionT1.setText("La clave debe ser un conjunto de números de 1 a n (con n menor a 9) en cualquier orden, en donde ninguno debe repetirse.");
        textoDescripcionPermutacionT1.setWrapStyleWord(true);
        textoDescripcionPermutacionT1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDescripcionPermutacionT1.setFocusable(false);
        textoDescripcionPermutacionT1.setOpaque(false);
        textoDescripcionPermutacionT1.setRequestFocusEnabled(false);
        textoDescripcionPermutacionT1.setVerifyInputWhenFocusTarget(false);
        textoDescripcionPermutacion1.setViewportView(textoDescripcionPermutacionT1);

        infoPermutacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N

        javax.swing.GroupLayout panelMuestraPermutacionLayout = new javax.swing.GroupLayout(panelMuestraPermutacion);
        panelMuestraPermutacion.setLayout(panelMuestraPermutacionLayout);
        panelMuestraPermutacionLayout.setHorizontalGroup(
            panelMuestraPermutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelMuestraPermutacionLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(infoPermutacion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoDescripcionPermutacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelMuestraPermutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMuestraPermutacionLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(clavePermutacionAlternativa, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelMuestraPermutacionLayout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(clavePermutacion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(698, 698, 698))
        );
        panelMuestraPermutacionLayout.setVerticalGroup(
            panelMuestraPermutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelMuestraPermutacionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelMuestraPermutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelMuestraPermutacionLayout.createSequentialGroup()
                        .addComponent(clavePermutacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(clavePermutacionAlternativa))
                    .addGroup(panelMuestraPermutacionLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(panelMuestraPermutacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoDescripcionPermutacion1, 0, 0, Short.MAX_VALUE)
                            .addComponent(infoPermutacion))))
                .addContainerGap())
        );

        panelMuestraPermutacion.setBounds(10, 165, 750, 93);
        panelMetodoPermutacion.add(panelMuestraPermutacion, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosClasicos.addTab("Permutación", new javax.swing.ImageIcon(getClass().getResource("/images/clasico.png")), panelMetodoPermutacion); // NOI18N

        panelMetodosClasicos.setBounds(0, 0, 780, 300);
        panelCifradoresClasicos.add(panelMetodosClasicos, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelMetodosClasicos.getAccessibleContext().setAccessibleName("");

        panelClasesCifrados.addTab("Cifradores Clásicos", panelCifradoresClasicos);

        panelMetodosBloque.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        panelMetodosBloque.setFocusable(false);
        panelMetodosBloque.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N

        panelMetodoDESS.setName("sdes"); // NOI18N

        panelDetallesDESS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesDESS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesDESS.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesDESS.setEnabled(false);
        panelDetallesDESS.setFocusable(false);

        textoDetallesDESS.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesDESS.setColumns(20);
        textoDetallesDESS.setEditable(false);
        textoDetallesDESS.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDetallesDESS.setLineWrap(true);
        textoDetallesDESS.setRows(5);
        textoDetallesDESS.setText("\nEl paso de texto cifrado a plano se realiza por \nFUERZA BRUTA.");
        textoDetallesDESS.setWrapStyleWord(true);
        textoDetallesDESS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesDESS.setFocusable(false);
        textoDetallesDESS.setOpaque(false);
        textoDetallesDESS.setRequestFocusEnabled(false);
        panelDetallesDESS.setViewportView(textoDetallesDESS);

        panelDetallesDESS.setBounds(80, 40, 300, 60);
        panelMetodoDESS.add(panelDetallesDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        infoDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoDESS.setBounds(30, 30, 40, 60);
        panelMetodoDESS.add(infoDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionDESS.setBorder(null);
        panelDescripcionDESS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionDESS.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionDESS.setEnabled(false);
        panelDescripcionDESS.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionDESS.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionDESS.setColumns(20);
        cajaDescripcionDESS.setEditable(false);
        cajaDescripcionDESS.setRows(4);
        cajaDescripcionDESS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionDESS.setEnabled(false);
        cajaDescripcionDESS.setFocusable(false);
        panelDescripcionDESS.setViewportView(cajaDescripcionDESS);

        panelDescripcionDESS.setBounds(10, 10, 380, 110);
        panelMetodoDESS.add(panelDescripcionDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveDESS.setText("Generar una clave");
        botonClaveDESS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveDESSActionPerformed(evt);
            }
        });
        botonClaveDESS.setBounds(10, 130, 340, 30);
        panelMetodoDESS.add(botonClaveDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarDESS.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarDESS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarDESS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarDESSActionPerformed(evt);
            }
        });
        botonLimpiarDESS.setBounds(360, 130, 30, 30);
        panelMetodoDESS.add(botonLimpiarDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveDESS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveDESS.setFocusable(false);

        claveDESS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveDESS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveDESSKeyTyped(evt);
            }
        });

        textoDescripcionDESS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcionDESS.setText("en formato binario y de exactamente 10 bits");
        textoDescripcionDESS.setFocusable(false);
        textoDescripcionDESS.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveDESSLayout = new javax.swing.GroupLayout(panelClaveDESS);
        panelClaveDESS.setLayout(panelClaveDESSLayout);
        panelClaveDESSLayout.setHorizontalGroup(
            panelClaveDESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveDESSLayout.createSequentialGroup()
                .addGroup(panelClaveDESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClaveDESSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textoDescripcionDESS, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                    .addGroup(panelClaveDESSLayout.createSequentialGroup()
                        .addGap(127, 127, 127)
                        .addComponent(claveDESS, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelClaveDESSLayout.setVerticalGroup(
            panelClaveDESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveDESSLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcionDESS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveDESS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveDESS.setBounds(10, 168, 380, 90);
        panelMetodoDESS.add(panelClaveDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        diagramaDESS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagramaDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sdes.png"))); // NOI18N
        diagramaDESS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diagramaDESS.setBounds(400, 10, 360, 250);
        panelMetodoDESS.add(diagramaDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("DES-Simplificado", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodoDESS); // NOI18N

        panelMetodoTripleDESS.setName("tsdes"); // NOI18N

        panelDetallesTripleDESS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesTripleDESS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesTripleDESS.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesTripleDESS.setEnabled(false);
        panelDetallesTripleDESS.setFocusable(false);

        textoDetallesTripleDESS.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesTripleDESS.setColumns(20);
        textoDetallesTripleDESS.setEditable(false);
        textoDetallesTripleDESS.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDetallesTripleDESS.setLineWrap(true);
        textoDetallesTripleDESS.setRows(5);
        textoDetallesTripleDESS.setText("\nEl paso de texto cifrado a plano se realiza por \nDESCIFRADO.\n");
        textoDetallesTripleDESS.setWrapStyleWord(true);
        textoDetallesTripleDESS.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesTripleDESS.setFocusable(false);
        textoDetallesTripleDESS.setOpaque(false);
        textoDetallesTripleDESS.setRequestFocusEnabled(false);
        panelDetallesTripleDESS.setViewportView(textoDetallesTripleDESS);

        panelDetallesTripleDESS.setBounds(80, 40, 300, 60);
        panelMetodoTripleDESS.add(panelDetallesTripleDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        infoTripleDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoTripleDESS.setBounds(30, 30, 40, 60);
        panelMetodoTripleDESS.add(infoTripleDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionTripleDESS.setBorder(null);
        panelDescripcionTripleDESS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionTripleDESS.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionTripleDESS.setEnabled(false);
        panelDescripcionTripleDESS.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionTripleDESS.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionTripleDESS.setColumns(20);
        cajaDescripcionTripleDESS.setEditable(false);
        cajaDescripcionTripleDESS.setRows(4);
        cajaDescripcionTripleDESS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionTripleDESS.setEnabled(false);
        cajaDescripcionTripleDESS.setFocusable(false);
        panelDescripcionTripleDESS.setViewportView(cajaDescripcionTripleDESS);

        panelDescripcionTripleDESS.setBounds(10, 10, 380, 110);
        panelMetodoTripleDESS.add(panelDescripcionTripleDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveTripleDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveTripleDESS.setText("Generar una clave");
        botonClaveTripleDESS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveTripleDESSActionPerformed(evt);
            }
        });
        botonClaveTripleDESS.setBounds(10, 130, 340, 30);
        panelMetodoTripleDESS.add(botonClaveTripleDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarTripleDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarTripleDESS.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarTripleDESS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarTripleDESS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarTripleDESSActionPerformed(evt);
            }
        });
        botonLimpiarTripleDESS.setBounds(360, 130, 30, 30);
        panelMetodoTripleDESS.add(botonLimpiarTripleDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveTripleDESS.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveTripleDESS.setFocusable(false);

        claveTripleDESS.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveTripleDESS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveTripleDESSKeyTyped(evt);
            }
        });

        textoDescripcionTripleDESS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcionTripleDESS.setText("en formato binario y de exactamente 30 bits");
        textoDescripcionTripleDESS.setFocusable(false);
        textoDescripcionTripleDESS.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveTripleDESSLayout = new javax.swing.GroupLayout(panelClaveTripleDESS);
        panelClaveTripleDESS.setLayout(panelClaveTripleDESSLayout);
        panelClaveTripleDESSLayout.setHorizontalGroup(
            panelClaveTripleDESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveTripleDESSLayout.createSequentialGroup()
                .addGroup(panelClaveTripleDESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClaveTripleDESSLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textoDescripcionTripleDESS, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                    .addGroup(panelClaveTripleDESSLayout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(claveTripleDESS, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelClaveTripleDESSLayout.setVerticalGroup(
            panelClaveTripleDESSLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveTripleDESSLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcionTripleDESS, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveTripleDESS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveTripleDESS.setBounds(10, 168, 380, 90);
        panelMetodoTripleDESS.add(panelClaveTripleDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        diagramaTripleDESS.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagramaTripleDESS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3sdes.png"))); // NOI18N
        diagramaTripleDESS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diagramaTripleDESS.setBounds(400, 10, 360, 250);
        panelMetodoTripleDESS.add(diagramaTripleDESS, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("Triple DES-Simplificado", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodoTripleDESS); // NOI18N

        panelMetodoDES.setName("des"); // NOI18N

        panelDetallesDES.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesDES.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesDES.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesDES.setEnabled(false);
        panelDetallesDES.setFocusable(false);

        textoDetallesDES.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesDES.setColumns(20);
        textoDetallesDES.setEditable(false);
        textoDetallesDES.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDetallesDES.setLineWrap(true);
        textoDetallesDES.setRows(5);
        textoDetallesDES.setText("\nEl paso de texto cifrado a plano se realiza por \nDESCIFRADO.");
        textoDetallesDES.setWrapStyleWord(true);
        textoDetallesDES.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesDES.setFocusable(false);
        textoDetallesDES.setOpaque(false);
        textoDetallesDES.setRequestFocusEnabled(false);
        panelDetallesDES.setViewportView(textoDetallesDES);

        panelDetallesDES.setBounds(80, 40, 300, 50);
        panelMetodoDES.add(panelDetallesDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        infoDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoDES.setBounds(30, 30, 40, 60);
        panelMetodoDES.add(infoDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionDES.setBorder(null);
        panelDescripcionDES.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionDES.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionDES.setEnabled(false);
        panelDescripcionDES.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionDES.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionDES.setColumns(20);
        cajaDescripcionDES.setEditable(false);
        cajaDescripcionDES.setRows(4);
        cajaDescripcionDES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionDES.setEnabled(false);
        cajaDescripcionDES.setFocusable(false);
        panelDescripcionDES.setViewportView(cajaDescripcionDES);

        panelDescripcionDES.setBounds(10, 10, 380, 110);
        panelMetodoDES.add(panelDescripcionDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveDES.setText("Generar una clave");
        botonClaveDES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveDESActionPerformed(evt);
            }
        });
        botonClaveDES.setBounds(10, 130, 340, 30);
        panelMetodoDES.add(botonClaveDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarDES.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarDES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarDES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarDESActionPerformed(evt);
            }
        });
        botonLimpiarDES.setBounds(360, 130, 30, 30);
        panelMetodoDES.add(botonLimpiarDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveDES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveDES.setFocusable(false);

        claveDES.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveDES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveDESKeyTyped(evt);
            }
        });

        textoDescripcionDES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcionDES.setText("en formato hexadecimal y de exactamente 16 dígitos");
        textoDescripcionDES.setFocusable(false);
        textoDescripcionDES.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveDESLayout = new javax.swing.GroupLayout(panelClaveDES);
        panelClaveDES.setLayout(panelClaveDESLayout);
        panelClaveDESLayout.setHorizontalGroup(
            panelClaveDESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveDESLayout.createSequentialGroup()
                .addGroup(panelClaveDESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClaveDESLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textoDescripcionDES, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                    .addGroup(panelClaveDESLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(claveDES, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelClaveDESLayout.setVerticalGroup(
            panelClaveDESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveDESLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcionDES, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveDES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveDES.setBounds(10, 168, 380, 90);
        panelMetodoDES.add(panelClaveDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        diagramaDES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagramaDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/des.png"))); // NOI18N
        diagramaDES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diagramaDES.setBounds(400, 10, 360, 250);
        panelMetodoDES.add(diagramaDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("DES", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodoDES); // NOI18N

        panelMetodoTDES.setName("tdes"); // NOI18N

        panelDetallesTDES.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesTDES.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesTDES.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesTDES.setEnabled(false);
        panelDetallesTDES.setFocusable(false);

        textoDetallesTDES.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesTDES.setColumns(20);
        textoDetallesTDES.setEditable(false);
        textoDetallesTDES.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDetallesTDES.setLineWrap(true);
        textoDetallesTDES.setRows(5);
        textoDetallesTDES.setText("\nEl paso de texto cifrado a plano se realiza por \nDESCIFRADO.");
        textoDetallesTDES.setWrapStyleWord(true);
        textoDetallesTDES.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesTDES.setFocusable(false);
        textoDetallesTDES.setOpaque(false);
        textoDetallesTDES.setRequestFocusEnabled(false);
        panelDetallesTDES.setViewportView(textoDetallesTDES);

        panelDetallesTDES.setBounds(80, 40, 300, 50);
        panelMetodoTDES.add(panelDetallesTDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        infoTDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoTDES.setBounds(30, 30, 40, 60);
        panelMetodoTDES.add(infoTDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionTDES.setBorder(null);
        panelDescripcionTDES.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionTDES.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionTDES.setEnabled(false);
        panelDescripcionTDES.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionTDES.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionTDES.setColumns(20);
        cajaDescripcionTDES.setEditable(false);
        cajaDescripcionTDES.setRows(4);
        cajaDescripcionTDES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionTDES.setEnabled(false);
        cajaDescripcionTDES.setFocusable(false);
        panelDescripcionTDES.setViewportView(cajaDescripcionTDES);

        panelDescripcionTDES.setBounds(10, 10, 380, 110);
        panelMetodoTDES.add(panelDescripcionTDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveTDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveTDES.setText("Generar una clave");
        botonClaveTDES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveTDESActionPerformed(evt);
            }
        });
        botonClaveTDES.setBounds(10, 130, 340, 30);
        panelMetodoTDES.add(botonClaveTDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarTDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarTDES.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarTDES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarTDES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarTDESActionPerformed(evt);
            }
        });
        botonLimpiarTDES.setBounds(360, 130, 30, 30);
        panelMetodoTDES.add(botonLimpiarTDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveTDES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveTDES.setFocusable(false);

        claveTDES.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveTDES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveTDESKeyTyped(evt);
            }
        });

        textoDescripcionTDES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcionTDES.setText("en formato hexadecimal y de exactamente 48 dígitos");
        textoDescripcionTDES.setFocusable(false);
        textoDescripcionTDES.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveTDESLayout = new javax.swing.GroupLayout(panelClaveTDES);
        panelClaveTDES.setLayout(panelClaveTDESLayout);
        panelClaveTDESLayout.setHorizontalGroup(
            panelClaveTDESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClaveTDESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClaveTDESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(claveTDES, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(textoDescripcionTDES, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelClaveTDESLayout.setVerticalGroup(
            panelClaveTDESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveTDESLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcionTDES, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveTDES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveTDES.setBounds(10, 168, 380, 90);
        panelMetodoTDES.add(panelClaveTDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        diagramaTDES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagramaTDES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3des.png"))); // NOI18N
        diagramaTDES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diagramaTDES.setBounds(400, 10, 360, 250);
        panelMetodoTDES.add(diagramaTDES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("Triple DES", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodoTDES); // NOI18N

        panelMetodoAES.setName("aes"); // NOI18N

        panelDetallesAES.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesAES.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesAES.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesAES.setEnabled(false);
        panelDetallesAES.setFocusable(false);

        textoDetallesAES.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesAES.setColumns(20);
        textoDetallesAES.setEditable(false);
        textoDetallesAES.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDetallesAES.setLineWrap(true);
        textoDetallesAES.setRows(5);
        textoDetallesAES.setText("El paso de texto cifrado a plano se realiza por \nDESCIFRADO.");
        textoDetallesAES.setWrapStyleWord(true);
        textoDetallesAES.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesAES.setFocusable(false);
        textoDetallesAES.setOpaque(false);
        textoDetallesAES.setRequestFocusEnabled(false);
        panelDetallesAES.setViewportView(textoDetallesAES);

        panelDetallesAES.setBounds(80, 50, 300, 40);
        panelMetodoAES.add(panelDetallesAES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        infoAES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoAES.setBounds(30, 30, 40, 60);
        panelMetodoAES.add(infoAES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionAES.setBorder(null);
        panelDescripcionAES.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionAES.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionAES.setEnabled(false);
        panelDescripcionAES.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionAES.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionAES.setColumns(20);
        cajaDescripcionAES.setEditable(false);
        cajaDescripcionAES.setRows(4);
        cajaDescripcionAES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionAES.setEnabled(false);
        cajaDescripcionAES.setFocusable(false);
        panelDescripcionAES.setViewportView(cajaDescripcionAES);

        panelDescripcionAES.setBounds(10, 10, 380, 110);
        panelMetodoAES.add(panelDescripcionAES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveAES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveAES.setText("Generar una clave");
        botonClaveAES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveAESActionPerformed(evt);
            }
        });
        botonClaveAES.setBounds(10, 130, 340, 30);
        panelMetodoAES.add(botonClaveAES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarAES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarAES.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarAES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarAES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarAESActionPerformed(evt);
            }
        });
        botonLimpiarAES.setBounds(360, 130, 30, 30);
        panelMetodoAES.add(botonLimpiarAES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveAES.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveAES.setFocusable(false);

        claveAES.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveAES.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveAESKeyTyped(evt);
            }
        });

        textoDescripcionAES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcionAES.setText("de 32 carácteres hexadecimales");
        textoDescripcionAES.setFocusable(false);
        textoDescripcionAES.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveAESLayout = new javax.swing.GroupLayout(panelClaveAES);
        panelClaveAES.setLayout(panelClaveAESLayout);
        panelClaveAESLayout.setHorizontalGroup(
            panelClaveAESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveAESLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClaveAESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(claveAES, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDescripcionAES, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelClaveAESLayout.setVerticalGroup(
            panelClaveAESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveAESLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcionAES, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveAES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveAES.setBounds(10, 168, 380, 90);
        panelMetodoAES.add(panelClaveAES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        diagramaAES.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagramaAES.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aes.png"))); // NOI18N
        diagramaAES.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        diagramaAES.setBounds(400, 10, 360, 250);
        panelMetodoAES.add(diagramaAES, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("AES", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodoAES); // NOI18N

        panelMetodosSPN.setBackground(new java.awt.Color(232, 232, 232));
        panelMetodosSPN.setName("spn"); // NOI18N

        panelDetallesSPN.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesSPN.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesSPN.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesSPN.setEnabled(false);
        panelDetallesSPN.setFocusable(false);

        textoDetallesSPN1.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesSPN1.setColumns(20);
        textoDetallesSPN1.setEditable(false);
        textoDetallesSPN1.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDetallesSPN1.setLineWrap(true);
        textoDetallesSPN1.setRows(5);
        textoDetallesSPN1.setText("Como se utilizan únicamente bloques de 4 bits para representar cada letra del alfabeto inglés, solo serán tomadas las letras de la A a la P. Los demás carácteres se eliminarán del texto y de la clave al ser procesados.\nEl CRIPTOANáLISIS solo se realiza para cadenas de 4 carácteres a través de una aproximación lineal.\n");
        textoDetallesSPN1.setWrapStyleWord(true);
        textoDetallesSPN1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesSPN1.setFocusable(false);
        textoDetallesSPN1.setOpaque(false);
        textoDetallesSPN1.setRequestFocusEnabled(false);
        panelDetallesSPN.setViewportView(textoDetallesSPN1);

        panelDetallesSPN.setBounds(80, 30, 300, 100);
        panelMetodosSPN.add(panelDetallesSPN, javax.swing.JLayeredPane.DEFAULT_LAYER);

        infoSPN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoSPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoSPN.setBounds(30, 30, 40, 80);
        panelMetodosSPN.add(infoSPN, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionSPN.setBorder(null);
        panelDescripcionSPN.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionSPN.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionSPN.setEnabled(false);
        panelDescripcionSPN.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionSPN.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionSPN.setColumns(20);
        cajaDescripcionSPN.setEditable(false);
        cajaDescripcionSPN.setRows(5);
        cajaDescripcionSPN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionSPN.setEnabled(false);
        cajaDescripcionSPN.setFocusable(false);
        panelDescripcionSPN.setViewportView(cajaDescripcionSPN);

        panelDescripcionSPN.setBounds(10, 10, 380, 120);
        panelMetodosSPN.add(panelDescripcionSPN, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveSPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveSPN.setText("Generar una clave");
        botonClaveSPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveSPNActionPerformed(evt);
            }
        });
        botonClaveSPN.setBounds(400, 10, 320, 30);
        panelMetodosSPN.add(botonClaveSPN, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarSPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarSPN.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarSPN.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarSPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarSPNActionPerformed(evt);
            }
        });
        botonLimpiarSPN.setBounds(730, 10, 30, 30);
        panelMetodosSPN.add(botonLimpiarSPN, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveSPN.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveSPN.setFocusable(false);

        claveSPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveSPN.setText("DKJENGDP");
        claveSPN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveSPNKeyTyped(evt);
            }
        });

        textoClaveSPN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoClaveSPN.setText("clave de 8 letras");
        textoClaveSPN.setFocusable(false);
        textoClaveSPN.setInheritsPopupMenu(false);

        permutacionSPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        permutacionSPN.setText("048C159D26AE37BF");
        permutacionSPN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                permutacionSPNKeyTyped(evt);
            }
        });

        textoPermutacionSPN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoPermutacionSPN.setText("permutacion de 16 en HEXA");
        textoPermutacionSPN.setFocusable(false);
        textoPermutacionSPN.setInheritsPopupMenu(false);

        sustitucionSPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        sustitucionSPN.setText("E4D12FB83A6C5907");
        sustitucionSPN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                sustitucionSPNKeyTyped(evt);
            }
        });

        textoSustitucionSPN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoSustitucionSPN.setText("sustitución de 16 en HEXA");
        textoSustitucionSPN.setFocusable(false);
        textoSustitucionSPN.setInheritsPopupMenu(false);

        numeroRondasSPN.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        numeroRondasSPN.setText("4");
        numeroRondasSPN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                numeroRondasSPNKeyTyped(evt);
            }
        });

        textoNumeroRondasSPN.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoNumeroRondasSPN.setText("número de rondas");
        textoNumeroRondasSPN.setFocusable(false);
        textoNumeroRondasSPN.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveSPNLayout = new javax.swing.GroupLayout(panelClaveSPN);
        panelClaveSPN.setLayout(panelClaveSPNLayout);
        panelClaveSPNLayout.setHorizontalGroup(
            panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClaveSPNLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClaveSPNLayout.createSequentialGroup()
                        .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoPermutacionSPN, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(textoNumeroRondasSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(textoSustitucionSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10))
                    .addGroup(panelClaveSPNLayout.createSequentialGroup()
                        .addComponent(textoClaveSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(claveSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(permutacionSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sustitucionSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(numeroRondasSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelClaveSPNLayout.setVerticalGroup(
            panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveSPNLayout.createSequentialGroup()
                .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoClaveSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(claveSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClaveSPNLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(textoPermutacionSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(textoSustitucionSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelClaveSPNLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelClaveSPNLayout.createSequentialGroup()
                                .addComponent(permutacionSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addGroup(panelClaveSPNLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(numeroRondasSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoNumeroRondasSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelClaveSPNLayout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(sustitucionSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        panelClaveSPN.setBounds(400, 50, 360, 160);
        panelMetodosSPN.add(panelClaveSPN, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelCriptoanalisis.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aproximación lineal", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelCriptoanalisis.setFocusable(false);

        textoParejas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoParejas.setText("Parejas texto plano - texto cifrado");
        textoParejas.setFocusable(false);
        textoParejas.setInheritsPopupMenu(false);

        rutaParejas.setEditable(false);
        rutaParejas.setHorizontalAlignment(javax.swing.JTextField.LEFT);

        buscarParejas.setText("...");
        buscarParejas.setToolTipText("Limpiar o reiniciar clave");
        buscarParejas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        buscarParejas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarParejasActionPerformed(evt);
            }
        });

        L1L2.setEditable(false);
        L1L2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        L1L2.setText("( - , - )");

        textoL1L3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoL1L3.setText("(L1, L2) :");
        textoL1L3.setFocusable(false);
        textoL1L3.setInheritsPopupMenu(false);

        calcularL1L3.setText("Calcular");
        calcularL1L3.setToolTipText("Limpiar o reiniciar clave");
        calcularL1L3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        calcularL1L3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calcularL1L3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCriptoanalisisLayout = new javax.swing.GroupLayout(panelCriptoanalisis);
        panelCriptoanalisis.setLayout(panelCriptoanalisisLayout);
        panelCriptoanalisisLayout.setHorizontalGroup(
            panelCriptoanalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCriptoanalisisLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelCriptoanalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(panelCriptoanalisisLayout.createSequentialGroup()
                        .addComponent(textoL1L3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(L1L2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(calcularL1L3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelCriptoanalisisLayout.createSequentialGroup()
                        .addComponent(rutaParejas, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarParejas, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(textoParejas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );
        panelCriptoanalisisLayout.setVerticalGroup(
            panelCriptoanalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCriptoanalisisLayout.createSequentialGroup()
                .addComponent(textoParejas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCriptoanalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rutaParejas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarParejas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelCriptoanalisisLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(calcularL1L3)
                    .addComponent(textoL1L3)
                    .addComponent(L1L2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        panelCriptoanalisis.setBounds(10, 140, 380, 120);
        panelMetodosSPN.add(panelCriptoanalisis, javax.swing.JLayeredPane.DEFAULT_LAYER);

        parejasPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        botonMuestrasSPN.setFont(new java.awt.Font("DejaVu Sans", 0, 11));
        botonMuestrasSPN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/bloc.png"))); // NOI18N
        botonMuestrasSPN.setText("Generar n parejas texto plano - texto cifrado");
        botonMuestrasSPN.setMaximumSize(new java.awt.Dimension(273, 31));
        botonMuestrasSPN.setMinimumSize(new java.awt.Dimension(273, 31));
        botonMuestrasSPN.setPreferredSize(new java.awt.Dimension(273, 31));
        botonMuestrasSPN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMuestrasSPNActionPerformed(evt);
            }
        });

        nParejas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nParejas.setText("8000");
        nParejas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nParejasKeyTyped(evt);
            }
        });

        nTexto.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        nTexto.setText("n");
        nTexto.setFocusable(false);
        nTexto.setInheritsPopupMenu(false);

        javax.swing.GroupLayout parejasPanelLayout = new javax.swing.GroupLayout(parejasPanel);
        parejasPanel.setLayout(parejasPanelLayout);
        parejasPanelLayout.setHorizontalGroup(
            parejasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, parejasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nTexto, javax.swing.GroupLayout.DEFAULT_SIZE, 18, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(nParejas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonMuestrasSPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3))
        );
        parejasPanelLayout.setVerticalGroup(
            parejasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parejasPanelLayout.createSequentialGroup()
                .addGroup(parejasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nTexto, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonMuestrasSPN, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nParejas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        parejasPanel.setBounds(400, 220, 360, 40);
        panelMetodosSPN.add(parejasPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("SPN", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodosSPN); // NOI18N

        panelMetodoCBCMAC.setName("cbcmac"); // NOI18N

        muestraCBCMAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cbcmac.png"))); // NOI18N
        muestraCBCMAC.setBounds(420, 10, 330, 250);
        panelMetodoCBCMAC.add(muestraCBCMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        infoCBCMAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoCBCMAC.setBounds(30, 40, 40, 40);
        panelMetodoCBCMAC.add(infoCBCMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveCBCMAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveCBCMAC.setText("Generar una clave");
        botonClaveCBCMAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveCBCMACActionPerformed(evt);
            }
        });
        botonClaveCBCMAC.setBounds(10, 130, 340, 30);
        panelMetodoCBCMAC.add(botonClaveCBCMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarCBCMAC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarCBCMAC.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarCBCMAC.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarCBCMAC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarCBCMACActionPerformed(evt);
            }
        });
        botonLimpiarCBCMAC.setBounds(360, 130, 30, 30);
        panelMetodoCBCMAC.add(botonLimpiarCBCMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveCBCMAC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveCBCMAC.setFocusable(false);

        claveCBCMAC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveCBCMAC.setText("0f1571c947d9e8590cb7add6af7f6798");
        claveCBCMAC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveCBCMACKeyTyped(evt);
            }
        });

        textoDescripcionCBCMAC.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcionCBCMAC.setText("de 32 carácteres hexadecimales");
        textoDescripcionCBCMAC.setFocusable(false);
        textoDescripcionCBCMAC.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveCBCMACLayout = new javax.swing.GroupLayout(panelClaveCBCMAC);
        panelClaveCBCMAC.setLayout(panelClaveCBCMACLayout);
        panelClaveCBCMACLayout.setHorizontalGroup(
            panelClaveCBCMACLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveCBCMACLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClaveCBCMACLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(claveCBCMAC, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDescripcionCBCMAC, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelClaveCBCMACLayout.setVerticalGroup(
            panelClaveCBCMACLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveCBCMACLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcionCBCMAC, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveCBCMAC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveCBCMAC.setBounds(10, 168, 380, 90);
        panelMetodoCBCMAC.add(panelClaveCBCMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDetallesCBCMAC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesCBCMAC.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesCBCMAC.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesCBCMAC.setEnabled(false);
        panelDetallesCBCMAC.setFocusable(false);

        textoDetallesCBCMAC.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesCBCMAC.setColumns(20);
        textoDetallesCBCMAC.setEditable(false);
        textoDetallesCBCMAC.setFont(new java.awt.Font("Tahoma", 0, 11));
        textoDetallesCBCMAC.setLineWrap(true);
        textoDetallesCBCMAC.setRows(5);
        textoDetallesCBCMAC.setText("Se utilizan bloques de 8 bits para representar cada letra del alfabeto inglés. Para este algoritmo se toma t=8 y n como la cantidad de letras del texto plano.");
        textoDetallesCBCMAC.setWrapStyleWord(true);
        textoDetallesCBCMAC.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesCBCMAC.setFocusable(false);
        textoDetallesCBCMAC.setOpaque(false);
        textoDetallesCBCMAC.setRequestFocusEnabled(false);
        textoDetallesCBCMAC.setVerifyInputWhenFocusTarget(false);
        panelDetallesCBCMAC.setViewportView(textoDetallesCBCMAC);

        panelDetallesCBCMAC.setBounds(80, 40, 300, 60);
        panelMetodoCBCMAC.add(panelDetallesCBCMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionCBCMAC.setBorder(null);
        panelDescripcionCBCMAC.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionCBCMAC.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionCBCMAC.setEnabled(false);
        panelDescripcionCBCMAC.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionCBCMAC.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionCBCMAC.setColumns(20);
        cajaDescripcionCBCMAC.setEditable(false);
        cajaDescripcionCBCMAC.setRows(4);
        cajaDescripcionCBCMAC.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionCBCMAC.setEnabled(false);
        cajaDescripcionCBCMAC.setFocusable(false);
        panelDescripcionCBCMAC.setViewportView(cajaDescripcionCBCMAC);

        panelDescripcionCBCMAC.setBounds(10, 10, 380, 110);
        panelMetodoCBCMAC.add(panelDescripcionCBCMAC, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("CBC-MAC", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodoCBCMAC); // NOI18N

        panelMetodoD1.setName("tablet"); // NOI18N

        infoD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/informacion.png"))); // NOI18N
        infoD1.setBounds(30, 40, 40, 40);
        panelMetodoD1.add(infoD1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveD1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese una clave", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelClaveD1.setFocusable(false);

        claveD1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        claveD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                claveD1ActionPerformed(evt);
            }
        });
        claveD1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                claveD1KeyTyped(evt);
            }
        });

        textoDescripcionD1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoDescripcionD1.setText("de 8 a 15 carácteres");
        textoDescripcionD1.setFocusable(false);
        textoDescripcionD1.setInheritsPopupMenu(false);

        javax.swing.GroupLayout panelClaveD1Layout = new javax.swing.GroupLayout(panelClaveD1);
        panelClaveD1.setLayout(panelClaveD1Layout);
        panelClaveD1Layout.setHorizontalGroup(
            panelClaveD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveD1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClaveD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(claveD1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                    .addComponent(textoDescripcionD1, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelClaveD1Layout.setVerticalGroup(
            panelClaveD1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveD1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(textoDescripcionD1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(claveD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveD1.setBounds(10, 168, 380, 90);
        panelMetodoD1.add(panelClaveD1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDetallesD1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        panelDetallesD1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDetallesD1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDetallesD1.setEnabled(false);
        panelDetallesD1.setFocusable(false);

        textoDetallesD1.setBackground(new java.awt.Color(232, 232, 232));
        textoDetallesD1.setColumns(20);
        textoDetallesD1.setEditable(false);
        textoDetallesD1.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        textoDetallesD1.setLineWrap(true);
        textoDetallesD1.setRows(5);
        textoDetallesD1.setText("Se utilizan bloques del tamaño de la clave, los cuales son cifrados según la tabla secreta generada a partir de la clave ingresada");
        textoDetallesD1.setWrapStyleWord(true);
        textoDetallesD1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(232, 232, 232), 0));
        textoDetallesD1.setFocusable(false);
        textoDetallesD1.setOpaque(false);
        textoDetallesD1.setRequestFocusEnabled(false);
        textoDetallesD1.setVerifyInputWhenFocusTarget(false);
        panelDetallesD1.setViewportView(textoDetallesD1);

        panelDetallesD1.setBounds(80, 40, 300, 60);
        panelMetodoD1.add(panelDetallesD1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelDescripcionD1.setBorder(null);
        panelDescripcionD1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panelDescripcionD1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panelDescripcionD1.setEnabled(false);
        panelDescripcionD1.setPreferredSize(new java.awt.Dimension(370, 110));

        cajaDescripcionD1.setBackground(new java.awt.Color(232, 232, 232));
        cajaDescripcionD1.setColumns(20);
        cajaDescripcionD1.setEditable(false);
        cajaDescripcionD1.setRows(4);
        cajaDescripcionD1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Descripción", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        cajaDescripcionD1.setEnabled(false);
        cajaDescripcionD1.setFocusable(false);
        panelDescripcionD1.setViewportView(cajaDescripcionD1);

        panelDescripcionD1.setBounds(10, 10, 380, 110);
        panelMetodoD1.add(panelDescripcionD1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelSecuenciaOcultamiento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Secuencia de ocultamiento", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelSecuenciaOcultamiento.setFocusable(false);

        botonSecuenciaOcultamiento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonSecuenciaOcultamiento.setActionCommand("Generar secuencia de ocultamiento");
        botonSecuenciaOcultamiento.setLabel("Generar secuencia de ocultamiento");
        botonSecuenciaOcultamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonSecuenciaOcultamientoActionPerformed(evt);
            }
        });

        secuenciaOcultamiento.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout panelSecuenciaOcultamientoLayout = new javax.swing.GroupLayout(panelSecuenciaOcultamiento);
        panelSecuenciaOcultamiento.setLayout(panelSecuenciaOcultamientoLayout);
        panelSecuenciaOcultamientoLayout.setHorizontalGroup(
            panelSecuenciaOcultamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSecuenciaOcultamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSecuenciaOcultamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(secuenciaOcultamiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(botonSecuenciaOcultamiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelSecuenciaOcultamientoLayout.setVerticalGroup(
            panelSecuenciaOcultamientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSecuenciaOcultamientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonSecuenciaOcultamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(secuenciaOcultamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        panelSecuenciaOcultamiento.setBounds(400, 130, 365, 130);
        panelMetodoD1.add(panelSecuenciaOcultamiento, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelTablaCifrado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Guardar tabla", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N
        panelTablaCifrado.setFocusable(false);

        botonTablaCifrado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/guardar.png"))); // NOI18N
        botonTablaCifrado.setText("Guardar tabla de cifrado");
        botonTablaCifrado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonTablaCifradoActionPerformed(evt);
            }
        });

        textoDescripcionSimboloD1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoDescripcionSimboloD1.setText("Separar las columnas con el símbolo:");
        textoDescripcionSimboloD1.setFocusable(false);
        textoDescripcionSimboloD1.setInheritsPopupMenu(false);

        tablaSimboloSeparador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tablaSimboloSeparador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaSimboloSeparadorKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelTablaCifradoLayout = new javax.swing.GroupLayout(panelTablaCifrado);
        panelTablaCifrado.setLayout(panelTablaCifradoLayout);
        panelTablaCifradoLayout.setHorizontalGroup(
            panelTablaCifradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaCifradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTablaCifradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonTablaCifrado, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addGroup(panelTablaCifradoLayout.createSequentialGroup()
                        .addComponent(textoDescripcionSimboloD1, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(tablaSimboloSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelTablaCifradoLayout.setVerticalGroup(
            panelTablaCifradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTablaCifradoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTablaCifradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoDescripcionSimboloD1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tablaSimboloSeparador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonTablaCifrado, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        panelTablaCifrado.setBounds(402, 10, 365, 120);
        panelMetodoD1.add(panelTablaCifrado, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelTablaCifrado.getAccessibleContext().setAccessibleName("Guardar tablas");

        botonClaveD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveD1.setText("Generar una clave");
        botonClaveD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveD1ActionPerformed(evt);
            }
        });
        botonClaveD1.setBounds(10, 130, 340, 30);
        panelMetodoD1.add(botonClaveD1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarD1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarD1.setToolTipText("Limpiar o reiniciar clave");
        botonLimpiarD1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonLimpiarD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarD1ActionPerformed(evt);
            }
        });
        botonLimpiarD1.setBounds(360, 130, 30, 30);
        panelMetodoD1.add(botonLimpiarD1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosBloque.addTab("D1", new javax.swing.ImageIcon(getClass().getResource("/images/bloque.png")), panelMetodoD1); // NOI18N

        panelMetodosBloque.setBounds(0, 0, 780, 300);
        panelCifradoresBloque.add(panelMetodosBloque, javax.swing.JLayeredPane.DEFAULT_LAYER);
        panelMetodosBloque.getAccessibleContext().setAccessibleName("Triple DES-Simplificado");

        panelClasesCifrados.addTab("Cifradores de Bloque", panelCifradoresBloque);

        panelMetodosPublicos.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        panelMetodosPublicos.setFocusable(false);
        panelMetodosPublicos.setFont(new java.awt.Font("Tahoma", 0, 10));

        panelMetodoRSA.setName("rsa"); // NOI18N
        panelMetodoRSA.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                panelMetodoRSAFocusGained(evt);
            }
        });

        panelClaveRSAab.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N

        tipoClaveRSAab.add(tipoClaveRSAd);
        tipoClaveRSAd.setText("d:");
        tipoClaveRSAd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveRSAdActionPerformed(evt);
            }
        });

        tipoClaveRSAab.add(tipoClaveRSAe);
        tipoClaveRSAe.setSelected(true);
        tipoClaveRSAe.setText("e:");
        tipoClaveRSAe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveRSAeActionPerformed(evt);
            }
        });

        cajaClaveRSAd.setEnabled(false);
        cajaClaveRSAd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaClaveRSAdKeyTyped(evt);
            }
        });

        cajaClaveRSAe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaClaveRSAeKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelClaveRSAabLayout = new javax.swing.GroupLayout(panelClaveRSAab);
        panelClaveRSAab.setLayout(panelClaveRSAabLayout);
        panelClaveRSAabLayout.setHorizontalGroup(
            panelClaveRSAabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveRSAabLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClaveRSAabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoClaveRSAd, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoClaveRSAe))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClaveRSAabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cajaClaveRSAd)
                    .addComponent(cajaClaveRSAe, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelClaveRSAabLayout.setVerticalGroup(
            panelClaveRSAabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveRSAabLayout.createSequentialGroup()
                .addGroup(panelClaveRSAabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoClaveRSAd, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaClaveRSAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClaveRSAabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoClaveRSAe, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaClaveRSAe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelClaveRSAabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cajaClaveRSAd, tipoClaveRSAd});

        panelClaveRSAabLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cajaClaveRSAe, tipoClaveRSAe});

        panelClaveRSAab.setBounds(10, 150, -1, 87);
        panelMetodoRSA.add(panelClaveRSAab, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClaveRSAnpq.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingrese", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("DejaVu Sans", 0, 11))); // NOI18N

        tipoClaveRSAnpq.add(tipoClaveRSAn);
        tipoClaveRSAn.setSelected(true);
        tipoClaveRSAn.setText("n:");
        tipoClaveRSAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveRSAnActionPerformed(evt);
            }
        });

        textoClaveRSAq.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoClaveRSAq.setText("q:");

        tipoClaveRSAnpq.add(tipoClaveRSApq);
        tipoClaveRSApq.setText("p:");
        tipoClaveRSApq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoClaveRSApqActionPerformed(evt);
            }
        });

        cajaClaveRSAp.setEnabled(false);
        cajaClaveRSAp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaClaveRSApKeyTyped(evt);
            }
        });

        cajaClaveRSAq.setEnabled(false);
        cajaClaveRSAq.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaClaveRSAqKeyTyped(evt);
            }
        });

        cajaClaveRSAn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cajaClaveRSAnActionPerformed(evt);
            }
        });
        cajaClaveRSAn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cajaClaveRSAnKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout panelClaveRSAnpqLayout = new javax.swing.GroupLayout(panelClaveRSAnpq);
        panelClaveRSAnpq.setLayout(panelClaveRSAnpqLayout);
        panelClaveRSAnpqLayout.setHorizontalGroup(
            panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelClaveRSAnpqLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelClaveRSAnpqLayout.createSequentialGroup()
                        .addGroup(panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tipoClaveRSApq, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelClaveRSAnpqLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(textoClaveRSAq, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cajaClaveRSAq, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cajaClaveRSAp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelClaveRSAnpqLayout.createSequentialGroup()
                        .addComponent(tipoClaveRSAn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cajaClaveRSAn, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)))
                .addContainerGap())
        );

        panelClaveRSAnpqLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {tipoClaveRSAn, tipoClaveRSApq});

        panelClaveRSAnpqLayout.setVerticalGroup(
            panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelClaveRSAnpqLayout.createSequentialGroup()
                .addGroup(panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoClaveRSAn, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cajaClaveRSAn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cajaClaveRSAp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipoClaveRSApq, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelClaveRSAnpqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cajaClaveRSAq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoClaveRSAq, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                .addGap(21, 21, 21))
        );

        panelClaveRSAnpqLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cajaClaveRSAp, cajaClaveRSAq, tipoClaveRSApq});

        panelClaveRSAnpq.setBounds(10, 35, 500, 115);
        panelMetodoRSA.add(panelClaveRSAnpq, javax.swing.JLayeredPane.DEFAULT_LAYER);

        tipoOptimizacionRSA.setText("  Optimización Bellare-Rogaway");
        tipoOptimizacionRSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoOptimizacionRSAActionPerformed(evt);
            }
        });
        tipoOptimizacionRSA.setBounds(10, 240, 250, -1);
        panelMetodoRSA.add(tipoOptimizacionRSA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonLimpiarRSA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reiniciar.png"))); // NOI18N
        botonLimpiarRSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonLimpiarRSAActionPerformed(evt);
            }
        });
        botonLimpiarRSA.setBounds(470, 200, 31, 31);
        panelMetodoRSA.add(botonLimpiarRSA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        botonClaveRSA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/clave.png"))); // NOI18N
        botonClaveRSA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonClaveRSAActionPerformed(evt);
            }
        });
        botonClaveRSA.setBounds(470, 160, 31, 31);
        panelMetodoRSA.add(botonClaveRSA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        diagramaRSA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        diagramaRSA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/RSA.png"))); // NOI18N
        diagramaRSA.setBounds(530, 20, 230, 240);
        panelMetodoRSA.add(diagramaRSA, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelMetodosPublicos.addTab("RSA", new javax.swing.ImageIcon(getClass().getResource("/images/clasico.png")), panelMetodoRSA); // NOI18N

        panelMetodosPublicos.setBounds(0, 0, 780, 300);
        panelCifradoresPublicos.add(panelMetodosPublicos, javax.swing.JLayeredPane.DEFAULT_LAYER);

        panelClasesCifrados.addTab("Cifradores de Clave Pública", panelCifradoresPublicos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(panelContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 798, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelClasesCifrados, javax.swing.GroupLayout.DEFAULT_SIZE, 780, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContenido, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelClasesCifrados, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonClaveD1ActionPerformed(java.awt.event.ActionEvent evt) {
        Random rnd = new Random();
        String clave = "";
        for (int x = 0; x < (8 + rnd.nextInt(7)); x++) {
            char claveChar = (char) (rnd.nextInt(25) + 65);
            clave = clave.concat(String.valueOf(claveChar));
        }
        claveD1.setText(clave);
    }

        private void claveD1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_claveD1KeyTyped
            char c = evt.getKeyChar();
            if ((!(Character.isLetter(c) || Character.isDigit(c)) && !Character.isWhitespace(c)) || claveD1.getText().length() > 15) {
                evt.consume();
            } else {
                if (Character.isLowerCase(c)) {
                    c = Character.toUpperCase(c);
                    claveD1.setText(claveD1.getText().concat(
                            String.valueOf(c)));
                    evt.consume();
                }
            }
        }//GEN-LAST:event_claveD1KeyTyped

        private void botonTablaCifradoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonTablaCifradoActionPerformed
            String claveCadena = claveD1.getText().toLowerCase();
            if (claveCadena.length() < 8 || claveCadena.length() > 15) {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegúrese que la clave está compuesta por 8 a 15 letras y/o números o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            } else {

                char separador;
                if (tablaSimboloSeparador.getText().length() == 0) {
                    separador = ';';
                } else {
                    separador = tablaSimboloSeparador.getText().charAt(0);
                }

                JFileChooser chooser = new JFileChooser();
                chooser.addChoosableFileFilter(new CSVFilter());
                chooser.showSaveDialog(this);

                File archivo = chooser.getSelectedFile();
                try {
                    PrintWriter writer = new PrintWriter(archivo + ".csv");
                    writer.print(D1Cipher.getCipherTable(claveCadena, separador));
                    writer.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }


        }//GEN-LAST:event_botonTablaCifradoActionPerformed

        private void botonLimpiarD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonLimpiarDesplazamiento1ActionPerformed
            claveD1.setText("");
        }//GEN-LAST:event_botonLimpiarDesplazamiento1ActionPerformed

        private void tablaSimboloSeparadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaSimboloSeparadorKeyTyped
            Character c = evt.getKeyChar();
            if (tablaSimboloSeparador.getText().length() == 1) {
                evt.consume();
            }
}//GEN-LAST:event_tablaSimboloSeparadorKeyTyped

        private void botonSecuenciaOcultamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonSecuenciaOcultamientoActionPerformed

            String textoCifrado = cajaTextoCifrado.getText().trim();
            cajaTextoCifrado.setText(textoCifrado);

            if (textoCifrado.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Para generar la secuencia de ocultamiento debe haber cifrado previamente el texto plano",
                        "Error al generar la secuencia", JOptionPane.ERROR_MESSAGE);
            } else {
                secuenciaOcultamiento.setText(D1Cipher.getHiddingSequence(textoCifrado));
            }
        }//GEN-LAST:event_botonSecuenciaOcultamientoActionPerformed

        private void claveD1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_claveD1ActionPerformed
            // TODO add your handling code here:
        }//GEN-LAST:event_claveD1ActionPerformed

    private void botonAbrirCifradoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonAbrirCifradoActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new TxTFilter());
        chooser.showOpenDialog(this);
        File archivo = chooser.getSelectedFile();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = "";
            String texto = "";
            while ((linea = reader.readLine()) != null) {
                texto = texto.concat(linea).concat("\n");
            }
            reader.close();
            cajaTextoCifrado.setText(texto);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }// GEN-LAST:event_botonAbrirCifradoActionPerformed

    /**
     * This method is called when inputs a key on boxText cajaTextoPlano and
     * tranforms the upperletters to lowerletters and prevents the inputs of
     * white spaces, symbols or numbers
     *
     * @param evt
     */
    private void cajaTextoPlanoKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cajaTextoPlanoKeyTyped
        char c = evt.getKeyChar();
        if (c < 32 || (c > 126 && c < 161) || c > 255) {
            evt.consume();
        }
    }// GEN-LAST:event_cajaTextoPlanoKeyTyped

    /**
     * This method is called when inputs a key on boxText cajaTextoPlano and
     * tranforms the lowerletter to supperletters and prevents the inputs of
     * white spaces, symbols or numbers
     *
     * @param evt
     */
    private void cajaTextoCifradoKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cajaTextoCifradoKeyTyped
        char c = evt.getKeyChar();
        if (c < 32 || (c > 126 && c < 161) || c > 255) {
            evt.consume();
        } else {
            if (Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
                cajaTextoCifrado.setText(cajaTextoCifrado.getText().concat(
                        String.valueOf(c)));
                evt.consume();
            }
        }
    }// GEN-LAST:event_cajaTextoCifradoKeyTyped

    private void encriptarDesplazamiento(String textoPlano) {
        if ((tipoClaveDesplazamientoNumero.isSelected() && claveDesplazamientoNumero.getText().isEmpty())
                || (claveDesplazamientoCaracter.getText().isEmpty() && tipoClaveDesplazamientoCaracter.isSelected())) {
            botonClaveDesplazamientoActionPerformed(null);
            int clave = Integer.parseInt(claveDesplazamientoNumero.getText());
            String textoCifrado = ShiftCipher.encryptMod189(textoPlano, clave);
            cajaTextoCifrado.setText(textoCifrado);
            cajaTextoPlano.setText(textoPlano);
        } else {
            if (tipoClaveDesplazamientoNumero.isSelected()) {
                try {
                    if (Integer.parseInt(claveDesplazamientoNumero.getText()) <= 188
                            && Integer.parseInt(claveDesplazamientoNumero.getText()) >= 0) {
                        int clave = Integer.parseInt(claveDesplazamientoNumero.getText());
                        String textoCifrado = ShiftCipher.encryptMod189(
                                textoPlano, clave);
                        cajaTextoCifrado.setText(textoCifrado);
                        cajaTextoPlano.setText(textoPlano);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ingrese una clave númerica válida o pruebe pulsando el botón\n          Generar una clave",
                            "Error al cifrar",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                if (claveDesplazamientoCaracter.getText().length() == 1) {
                    int clave = Integer.parseInt(claveDesplazamientoNumero.getText());
                    String textoCifrado = ShiftCipher.encryptMod189(textoPlano,
                            clave);
                    cajaTextoCifrado.setText(textoCifrado);
                    cajaTextoPlano.setText(textoPlano);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Ingrese una clave válida o pruebe pulsando el botón\n          Generar una clave",
                            "Error al cifrar",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void encriptarSustitucion(String textoPlano) {
        if (claveSustitucion.getText().isEmpty()) {
            botonClaveSustitucionActionPerformed(null);
            String clave = claveSustitucion.getText();
            String textoCifrado = SubstitutionCipher.encrypt(textoPlano, clave);
            cajaTextoCifrado.setText(textoCifrado);
            cajaTextoPlano.setText(textoPlano);
        } else {
            if (claveSustitucion.getText().length() == 26) {
                String clave = claveSustitucion.getText();
                String textoCifrado = SubstitutionCipher.encrypt(textoPlano,
                        clave);
                cajaTextoCifrado.setText(textoCifrado);
                cajaTextoPlano.setText(textoPlano);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nIngrese los 26 simbolos de la clave para poder continuar o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void encriptarAffine(String textoPlano) {
        if (claveAffineA.getText().isEmpty()
                && claveAffineB.getText().isEmpty()) {
            botonClaveAffineActionPerformed(null);
            int claveA = Integer.parseInt(claveAffineA.getText());
            int claveB = Integer.parseInt(claveAffineB.getText());
            String textoCifrado = AffineCipher.encrypt(textoPlano, claveA,
                    claveB);
            cajaTextoCifrado.setText(textoCifrado);
            cajaTextoPlano.setText(textoPlano);
        } else {
            if (claveAffineA.getText().isEmpty()
                    || claveAffineB.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "Ingrese los dos componentes de la clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    if (mcd(Integer.parseInt(claveAffineA.getText()), 26) == 1) {
                        int claveA = Integer.parseInt(claveAffineA.getText());
                        int claveB = Integer.parseInt(claveAffineB.getText());
                        String textoCifrado = AffineCipher.encrypt(textoPlano,
                                claveA, claveB);
                        cajaTextoCifrado.setText(textoCifrado);
                        cajaTextoPlano.setText(textoPlano);
                    } else {
                        JOptionPane.showMessageDialog(
                                this,
                                "La clave ingresada no es valida\nA debe ser primo relativo con 26 y B un numero entero positivo menor a 26\n          Generar una clave",
                                "Error al cifrar",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            this,
                            "La clave ingresada no es valida\nEl caracter A debe ser primo relativo con 26 y B un numero entero positivo menor a 26\n          Generar una clave",
                            "Error al cifrar",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void encriptarVigenere(String textoPlano) {
        if (claveVigenere.getText().isEmpty()) {
            botonClaveVigenereActionPerformed(null);
            String clave = claveVigenere.getText();
            String textoCifrado = VigenereCipher.encrypt(textoPlano, clave);
            cajaTextoCifrado.setText(textoCifrado);
            cajaTextoPlano.setText(textoPlano);
        } else {
            if (claveVigenere.getText().length() < 9) {
                try {
                    String clave = claveVigenere.getText();
                    String textoCifrado = VigenereCipher.encrypt(textoPlano,
                            clave);
                    cajaTextoCifrado.setText(textoCifrado);
                    cajaTextoPlano.setText(textoPlano);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            this,
                            "La clave ingresada no es valida\nIngrese una clave de por lo menos 3 caracteres\n          Generar una clave",
                            "Error al cifrar",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nIngrese una clave de máximo 8 caracteres\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void encriptarHill(String textoPlano) {
        Matrix clave = null;
        if (tipoClaveHill2.isSelected()) {
            if (claveHill1.getText().isEmpty()
                    && claveHill2.getText().isEmpty()
                    && claveHill4.getText().isEmpty()
                    && claveHill5.getText().isEmpty()) {
                botonClaveHillActionPerformed(null);
                double[][] valoresClave = {
                    {Double.parseDouble(claveHill1.getText()),
                        Double.parseDouble(claveHill2.getText())},
                    {Double.parseDouble(claveHill4.getText()),
                        Double.parseDouble(claveHill5.getText())}};
                clave = new Matrix(valoresClave);
            } else {
                if (claveHill1.getText().isEmpty()
                        || claveHill2.getText().isEmpty()
                        || claveHill4.getText().isEmpty()
                        || claveHill5.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Ingrese todos los valores de la clave",
                            "Error al cifrar", JOptionPane.ERROR_MESSAGE);
                } else {
                    double[][] valoresClave = {
                        {Double.parseDouble(claveHill1.getText()),
                            Double.parseDouble(claveHill2.getText())},
                        {Double.parseDouble(claveHill4.getText()),
                            Double.parseDouble(claveHill5.getText())}};
                    clave = new Matrix(valoresClave);
                }
            }
        } else {
            if (claveHill1.getText().isEmpty()
                    && claveHill2.getText().isEmpty()
                    && claveHill4.getText().isEmpty()
                    && claveHill5.getText().isEmpty()
                    && claveHill3.getText().isEmpty()
                    && claveHill6.getText().isEmpty()
                    && claveHill7.getText().isEmpty()
                    && claveHill8.getText().isEmpty()
                    && claveHill9.getText().isEmpty()) {
                botonClaveHillActionPerformed(null);
                double[][] valoresClave = {
                    {Double.parseDouble(claveHill1.getText()),
                        Double.parseDouble(claveHill2.getText()),
                        Double.parseDouble(claveHill3.getText())},
                    {Double.parseDouble(claveHill4.getText()),
                        Double.parseDouble(claveHill5.getText()),
                        Double.parseDouble(claveHill6.getText())},
                    {Double.parseDouble(claveHill7.getText()),
                        Double.parseDouble(claveHill8.getText()),
                        Double.parseDouble(claveHill9.getText())}};
                clave = new Matrix(valoresClave);
            } else {
                if (claveHill1.getText().isEmpty()
                        || claveHill2.getText().isEmpty()
                        || claveHill4.getText().isEmpty()
                        || claveHill5.getText().isEmpty()
                        || claveHill3.getText().isEmpty()
                        || claveHill6.getText().isEmpty()
                        || claveHill7.getText().isEmpty()
                        || claveHill8.getText().isEmpty()
                        || claveHill9.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Ingrese todos los valores de la clave",
                            "Error al cifrar", JOptionPane.ERROR_MESSAGE);
                } else {
                    double[][] valoresClave = {
                        {Double.parseDouble(claveHill1.getText()),
                            Double.parseDouble(claveHill2.getText()),
                            Double.parseDouble(claveHill3.getText())},
                        {Double.parseDouble(claveHill4.getText()),
                            Double.parseDouble(claveHill5.getText()),
                            Double.parseDouble(claveHill6.getText())},
                        {Double.parseDouble(claveHill7.getText()),
                            Double.parseDouble(claveHill8.getText()),
                            Double.parseDouble(claveHill9.getText())}};
                    clave = new Matrix(valoresClave);
                }
            }
        }
        int det = (int) Math.round(clave.det());
        if (mcd(det, 26) == 1) {
            String textoCifrado = HillCipher.encrypt(textoPlano, clave);
            cajaTextoCifrado.setText(textoCifrado);
            cajaTextoPlano.setText(textoPlano);
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que el MCD del determinante y 26 sea 1",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void encriptarPermutacion(String textoPlano) {
        String clave = clavePermutacion.getText();
        try {
            String textoCifrado = "";
            if (clavePermutacionAlternativa.isSelected()) {
                textoCifrado = PermutationCipher.encryptAlternate(textoPlano,
                        clave);
                textoCifrado = PermutationCipher.encryptAlternate(
                        textoCifrado.toUpperCase(), clave);
            } else {
                textoCifrado = PermutationCipher.encrypt(textoPlano, clave);
            }
            cajaTextoCifrado.setText(textoCifrado);
            cajaTextoPlano.setText(textoPlano);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave es una secuencia de 1 a n (con n menor a 9) en cualquier orden sin repetir o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }

    private void encriptarSPN(String textoPlano) {
        String clave = claveSPN.getText();
        if (clave.length() != 8) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave es una palabra de 8 caracteres o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int nr = Integer.parseInt(numeroRondasSPN.getText());
                if (nr < 1 || nr > 25) {
                    JOptionPane.showMessageDialog(
                            this,
                            "El número de rondas es inválido\nAsegurese que el numero de rondas sea mayor a 0 y menor a 16 o pruebe pulsando el botón\n          Generar una clave",
                            "Error al cifrar",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (permutacionSPN.getText().length() != 16
                            || sustitucionSPN.getText().length() != 16) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Las funciones de permutación o sustitución son inválidas\nAsegurese que sean secuencias de 0 a F en cualquier orden sin repetir ningun valor o pruebe pulsando el botón\n          Generar una clave",
                                "Error al cifrar",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        char[] permutacionChar = permutacionSPN.getText().toCharArray();
                        char[] sustitucionChar = sustitucionSPN.getText().toCharArray();
                        int[] permutacion = new int[16];
                        int[] sustitucion = new int[16];
                        for (int x = 0; x < 16; x++) {
                            permutacion[x] = (Integer.parseInt(
                                    String.valueOf(permutacionChar[x]), 16)) + 1;
                            sustitucion[x] = Integer.parseInt(
                                    String.valueOf(sustitucionChar[x]), 16);
                        }
                        try {
                            // System.out.println("textoPlano.length() " +
                            // textoPlano.length());
                            while (textoPlano.length() % 4 != 0) {
                                textoPlano = textoPlano.concat("a");
                            }
                            // System.out.println("textoPlano.length() after" +
                            // textoPlano.length());
                            String secret = "";
                            for (int y = 0; y < textoPlano.length(); y = y + 4) {
                                secret = secret.concat(SubstitutionPermutationNetworkCipher.encrypt(textoPlano.substring(
                                        y, (y + 4)),
                                        sustitucion,
                                        permutacion, clave, nr));
                            }
                            cajaTextoCifrado.setText(secret.toUpperCase());
                            cajaTextoPlano.setText(textoPlano);
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Los valores ingresados son inválidos\no pruebe pulsando el botón\n          Generar una clave",
                                    "Error al cifrar",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "El número de rondas es inválido\nAsegurese que el numero de rondas sea mayor a 0 y menor a 25 o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void encriptarSDES(String textoPlano) {
        try {
            String textoHexa = "";
            String textoCifrado = "";

            String clave = claveDESS.getText();
            if (clave.length() == 10) {
                for (int x = 0; x < textoPlano.length(); x++) {
                    textoHexa = cpcommonmethods.HexTools.fromASCIIStringToHexString(textoPlano.substring(x,
                            x + 1));
                    textoCifrado = textoCifrado.concat(SimplifiedDESCipher.encryptDecrypt(textoHexa, clave, true));
                }
                cajaTextoCifrado.setText(textoCifrado);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 10 bits o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 10 bits o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.print(e.toString());
        }
    }

    private void encriptarTSDES(String textoPlano) {
        try {
            String textoHexa = "";
            String textoCifrado = "";

            String clave = claveTripleDESS.getText();
            if (clave.length() == 30) {
                String stringTemp = "";
                for (int x = 0; x < textoPlano.length(); x++) {

                    textoHexa = cpcommonmethods.HexTools.fromASCIIStringToHexString(textoPlano.substring(x,
                            x + 1));

                    stringTemp = SimplifiedDESCipher.encryptDecrypt(textoHexa,
                            clave.substring(0, 10), true);
                    stringTemp = SimplifiedDESCipher.encryptDecrypt(stringTemp,
                            clave.substring(10, 20), false);
                    textoCifrado = textoCifrado.concat(SimplifiedDESCipher.encryptDecrypt(stringTemp,
                            clave.substring(20, 30), true));
                }
                cajaTextoCifrado.setText(textoCifrado);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 30 bits o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 10 bits o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.print(e.toString());
        }
    }

    private void encriptarDES(String textoPlano) {

        try {
            cajaTextoPlano.setText(textoPlano);
            String claveHexa = "";
            String textoHexa = "";
            String textoCifrado = "";
            Random rand = new Random();
            while (textoPlano.length() % 8 != 0) {
                char letra = (char) (rand.nextInt(25) + 65);
                textoPlano = textoPlano.concat(String.valueOf(letra));
            }

            claveHexa = claveDES.getText();
            if (claveHexa.length() == 16) {
                for (int x = 0; x < textoPlano.length(); x = x + 8) {
                    textoHexa = cpcommonmethods.HexTools.fromASCIIStringToHexString(textoPlano.substring(x,
                            x + 8));
                    textoCifrado = textoCifrado.concat(DESCipher.encryptDecrypt(textoHexa, claveHexa, true));
                }
                cajaTextoCifrado.setText(textoCifrado);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 16 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 16 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.print(e.toString());
        }
    }

    private void encriptarTDES(String textoPlano) {

        try {
            cajaTextoPlano.setText(textoPlano);
            String claveHexa = "";
            String textoHexa = "";
            String textoCifrado = "";
            Random rand = new Random();
            while (textoPlano.length() % 8 != 0) {
                char letra = (char) (rand.nextInt(25) + 65);
                textoPlano = textoPlano.concat(String.valueOf(letra));
            }

            claveHexa = claveTDES.getText();
            if (claveHexa.length() == 48) {
                String stringTemp = "";
                for (int x = 0; x < textoPlano.length(); x = x + 8) {
                    textoHexa = cpcommonmethods.HexTools.fromASCIIStringToHexString(textoPlano.substring(x,
                            x + 8));
                    stringTemp = DESCipher.encryptDecrypt(textoHexa,
                            claveHexa.substring(0, 16), true);
                    stringTemp = DESCipher.encryptDecrypt(stringTemp,
                            claveHexa.substring(16, 32), false);
                    textoCifrado = textoCifrado.concat(DESCipher.encryptDecrypt(stringTemp,
                            claveHexa.substring(32, 48), true));
                }
                cajaTextoCifrado.setText(textoCifrado);
                cajaTextoPlano.setText(textoPlano);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 48 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 48 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.print(e.toString());
        }
    }

    private void encriptarAES(String textoPlano) {

        try {
            String clave = claveAES.getText();
            if (clave.length() != 32) {
                throw new NumberFormatException();
            }
            String textoCifrado = AESCipher.longEncrypt(textoPlano, clave);
            cajaTextoCifrado.setText(textoCifrado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegúrese que la clave está compuesta por 32 hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }

    private void encriptarCBCMAC(String textoPlano) {
        String claveCadena = claveAES.getText();
        try {
            if (claveCadena.length() != 32) {
                throw new Exception("Clave muy corta o muy larga");
            }
            int[] clave = new int[16];
            for (int i = 0; i < 16; i++) {
                int j = i * 2;
                clave[i] = Integer.parseInt(claveCadena.substring(j, j + 2), 16);
            }
            cajaTextoCifrado.setText(CBCMac.encrypt(textoPlano, clave));
            cajaTextoPlano.setText(textoPlano);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegúrese que la clave está compuesta por 32 hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }

    private void encriptarTablet(String textoPlano) {
        String claveCadena = claveD1.getText();
        if (claveCadena.length() < 8 || claveCadena.length() > 15) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegúrese que la clave está compuesta por 8 a 15 letras y/o números o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                cajaTextoCifrado.setText(D1Cipher.encrypt(textoPlano, claveCadena));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegúrese que la clave está compuesta por 8 a 15 letras y/o números o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void encriptarRSA(String textoPlano) {
        try {
            String result = "";
            String n = cajaClaveRSAn.getText();
            String p = cajaClaveRSAp.getText();
            String q = cajaClaveRSAq.getText();
            String e = cajaClaveRSAe.getText();
            String d = cajaClaveRSAd.getText();

            if (tipoClaveRSAn.isSelected()) {
                tipoClaveRSAeActionPerformed(null);
                if (n.isEmpty()) {
                    throw new Exception(
                            "Es necesario ingresar el valor de n para cifrar el texto.");
                }
                if (e.isEmpty()) {
                    throw new Exception(
                            "Es necesario ingresar el valor de e para cifrar el texto.");
                }
                if (tipoOptimizacionRSA.isSelected()) {
                    result = BellareRogawayCipher.encrypt(textoPlano, n, e);
                } else {
                    result = RSACipher.encrypt(n, e, textoPlano);
                }
            } else {
                if (p.isEmpty() || q.isEmpty()) {
                    throw new Exception(
                            "Es necesario ingresar los valores de p y q para cifrar el texto.");
                }
                if (tipoClaveRSAd.isSelected()) {
                    tipoClaveRSAeActionPerformed(null);
                    if (d.isEmpty()) {
                        throw new Exception(
                                "Es necesario ingresar el valor de e para cifrar el texto.");
                    } else {
                        e = RSACipher.calculateInverse(d, p, q);
                    }
                } else {
                    if (e.isEmpty()) {
                        throw new Exception(
                                "Es necesario ingresar el valor de e para cifrar el texto.");
                    } else {
                        d = RSACipher.calculateInverse(e, p, q);
                    }
                }
                if (tipoOptimizacionRSA.isSelected()) {
                    result = BellareRogawayCipher.encrypt(textoPlano, p, q, e);
                } else {
                    result = RSACipher.encrypt(p, q, e, textoPlano);
                }
                cajaClaveRSAn.setText(RSACipher.calculateN(p, q));
                cajaClaveRSAd.setText(d);
                cajaClaveRSAe.setText(e);
            }

            cajaTextoCifrado.setText(result);
            cajaTextoPlano.setText(textoPlano);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "El formato de la clave es inválido. Asegúrese de usar únicamente números en formato decimal.",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
        } catch (OutOfMemoryError e) {
            JOptionPane.showMessageDialog(this,
                    "Se ha excedido la capacidad de máquina.",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void botonEncriptarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonEncriptarActionPerformed
        if (cajaTextoPlano.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un texto plano para cifrarlo", "Error al cifrar",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            String metodo = "";
            if (panelClasesCifrados.getSelectedIndex() == 0) {
                metodo = panelMetodosClasicos.getSelectedComponent().getName();
            } else if (panelClasesCifrados.getSelectedIndex() == 1) {
                metodo = panelMetodosBloque.getSelectedComponent().getName();
            } else {
                metodo = panelMetodosPublicos.getSelectedComponent().getName();
            }
            String textoPlano = cajaTextoPlano.getText();
            String textoPlanoMod26 = Code.removeCharactersOutOfMod26(textoPlano).toLowerCase();
            String textoPlanoMod189 = Code.removeCharactersOutOfMod189(textoPlano);

            if (metodo.equals("desplazamiento")) {
                encriptarDesplazamiento(textoPlanoMod189);
            }
            if (metodo.equals("sustitucion")) {
                encriptarSustitucion(textoPlanoMod26);
            }
            if (metodo.equals("affine")) {
                encriptarAffine(textoPlanoMod26);
            }
            if (metodo.equals("vigenere")) {
                encriptarVigenere(textoPlanoMod26);
            }
            if (metodo.equals("hill")) {
                encriptarHill(textoPlanoMod26);
            }
            if (metodo.equals("permutacion")) {
                encriptarPermutacion(textoPlanoMod26);
            }
            if (metodo.equals("spn")) {
                encriptarSPN(textoPlanoMod189);
            }
            if (metodo.equals("sdes")) {
                encriptarSDES(textoPlanoMod189);
            }
            if (metodo.equals("tsdes")) {
                encriptarTSDES(textoPlanoMod189);
            }
            if (metodo.equals("des")) {
                encriptarDES(textoPlanoMod189);
            }
            if (metodo.equals("tdes")) {
                encriptarTDES(textoPlanoMod189);
            }
            if (metodo.equals("aes")) {
                encriptarAES(textoPlano);
            }
            if (metodo.equals("cbcmac")) {
                encriptarCBCMAC(textoPlano);
            }
            if (metodo.equals("rsa")) {
                encriptarRSA(textoPlano);
            }
            if (metodo.equals("tablet")) {
                encriptarTablet(textoPlano);
            }
        }
    }// GEN-LAST:event_botonEncriptarActionPerformed

    private void fuerzaBrutaDesplazamiento(String textoCifrado) {
        String[] posibilidades = ShiftCipher.cryptoAnalysisMod189(textoCifrado);
        String textoPlano = "";
        for (int pos = 0; pos < posibilidades.length; pos++) {
            int[] decode = {pos};
            String texto = Code.decodeMod189(decode);
            textoPlano = textoPlano.concat("Clave " + pos + ": "
                    + texto.concat("\n"));
            textoPlano = textoPlano.concat(posibilidades[pos]).concat("\n\n");
        }
        cajaTextoPlano.setText(textoPlano);
        cajaTextoCifrado.setText(textoCifrado);
        // Frecuencias
        ArrayList<LettersOcurrence> frecLetras = LettersOcurrence.frequenciesMod189(Code.encodeMod189(textoCifrado));
        Object[][] datos = new Object[189][2];
        String etiquetas[] = {"Letra", "Frecuencia"};
        for (LettersOcurrence frecuencia : frecLetras) {
            int[] aux = {frecuencia.getLetter()};
            int letra = frecuencia.getLetter();
            int frec = frecuencia.getFrequency();
            datos[letra][0] = Code.decodeMod189(aux);
            datos[letra][1] = frec;
        }
        pintarFrecuencias(tablaResultadosDesplazamiento, 2, datos, etiquetas);
        // Frecuencias bigramas
        ArrayList<BigramsOcurrence> frecBigramas = BigramsOcurrence.frequenciesMod189(textoCifrado);
        int i = 0;
        datos = new Object[frecBigramas.size()][6];
        String etiquetas2[] = {"Bigrama", "Frec"};
        for (BigramsOcurrence frecuencia : frecBigramas) {
            int[] aux = {frecuencia.getFirstLetter(),
                frecuencia.getSecondLetter()};
            int frec = frecuencia.getFrequency();
            datos[i][0] = Code.decodeMod189(aux);
            datos[i][1] = frec;
            i++;
        }
        pintarFrecuencias(tablaResultadosDesplazamientoBigramas, 4, datos,
                etiquetas2);
        // Frecuencias trigramas
        ArrayList<TrigramsOcurrence> frecTrigramas = TrigramsOcurrence.frequenciesMod189(textoCifrado);
        i = 0;
        datos = new Object[frecTrigramas.size()][6];
        String etiquetas3[] = {"Trigrama", "Frec"};
        for (TrigramsOcurrence frecuencia : frecTrigramas) {
            int[] aux = {frecuencia.getFirstLetter(),
                frecuencia.getSecondLetter(), frecuencia.getThirdLetter()};
            int frec = frecuencia.getFrequency();
            datos[i][0] = Code.decodeMod189(aux);
            datos[i][1] = frec;
            i++;
        }
        pintarFrecuencias(tablaResultadosDesplazamientoTrigramas, 6, datos,
                etiquetas3);
        LettersOcurrence masFrecuente = LettersOcurrence.greatest(frecLetras);
        int probable = masFrecuente.getLetter() - 69;
        if (probable < 0) {
            probable += 189;
        }
        textoMasProbablesClavesDesplazamientoR.setText(Code.decodeMod189(
                new int[]{probable}).concat(" = " + probable));
    }

    private void analisisFrecuenciasSustitucion(String textoCifrado) {
        // Frecuencias letras
        ArrayList<LettersOcurrence> frecLetras = LettersOcurrence.frequencies(Code.encodeMod26(textoCifrado));

        Object[][] datos = new Object[26][6];
        String etiquetas[] = {"Letra", "Frec"};
        for (LettersOcurrence frecuencia : frecLetras) {
            int[] aux = {frecuencia.getLetter()};
            int letra = frecuencia.getLetter();
            int frec = frecuencia.getFrequency();
            datos[letra][0] = Code.decodeMod26(aux).toUpperCase();
            datos[letra][1] = frec;
        }
        pintarFrecuencias(tablaResultadosSusLetras, 2, datos, etiquetas);
        // Frecuencias bigramas
        ArrayList<BigramsOcurrence> frecBigramas = BigramsOcurrence.frequenciesMod26(textoCifrado);
        int i = 0;
        datos = new Object[frecBigramas.size()][6];
        String etiquetas2[] = {"Bigrama", "Frec"};
        for (BigramsOcurrence frecuencia : frecBigramas) {
            int[] aux = {frecuencia.getFirstLetter(),
                frecuencia.getSecondLetter()};
            int frec = frecuencia.getFrequency();
            datos[i][0] = Code.decodeMod26(aux).toUpperCase();
            datos[i][1] = frec;
            i++;
        }
        pintarFrecuencias(tablaResultadosSusBigramas, 4, datos, etiquetas2);
        // Frecuencias trigramas
        ArrayList<TrigramsOcurrence> frecTrigramas = TrigramsOcurrence.frequenciesMod26(textoCifrado);
        i = 0;
        datos = new Object[frecTrigramas.size()][6];
        String etiquetas3[] = {"Trigrama", "Frec"};
        for (TrigramsOcurrence frecuencia : frecTrigramas) {
            int[] aux = {frecuencia.getFirstLetter(),
                frecuencia.getSecondLetter(), frecuencia.getThirdLetter()};
            int frec = frecuencia.getFrequency();
            datos[i][0] = Code.decodeMod26(aux).toUpperCase();
            datos[i][1] = frec;
            i++;
        }
        pintarFrecuencias(tablaResultadosSusTrigramas, 6, datos, etiquetas3);

        cajaTextoPlano.setText(textoCifrado);
        cajaTextoCifrado.setText(textoCifrado);
        textoPlanoInicial = cajaTextoCifrado.getText();
    }

    private void criptoAnalisisAffine(String textoCifrado) {
        String textoPlano = "";
        // Frecuencias letras
        ArrayList<LettersOcurrence> frecLetras = LettersOcurrence.frequencies(Code.encodeMod26(textoCifrado));

        Object[][] datos = new Object[26][6];
        String etiquetas[] = {"Letra", "Frec"};
        for (LettersOcurrence frecuencia : frecLetras) {
            int[] aux = {frecuencia.getLetter()};
            int letra = frecuencia.getLetter();
            int frec = frecuencia.getFrequency();
            datos[letra][0] = Code.decodeMod26(aux);
            datos[letra][1] = frec;
        }
        pintarFrecuencias(tablaResultadosAffineLetras, 2, datos, etiquetas);
        // Frecuencias bigramas
        ArrayList<BigramsOcurrence> frecBigramas = BigramsOcurrence.frequenciesMod26(textoCifrado);
        int i = 0;
        datos = new Object[frecBigramas.size()][6];
        String etiquetas2[] = {"Bigrama", "Frec"};
        for (BigramsOcurrence frecuencia : frecBigramas) {
            int[] aux = {frecuencia.getFirstLetter(),
                frecuencia.getSecondLetter()};
            int frec = frecuencia.getFrequency();
            datos[i][0] = Code.decodeMod26(aux);
            datos[i][1] = frec;
            i++;
        }
        pintarFrecuencias(tablaResultadosAffineBigramas, 4, datos, etiquetas2);
        // Frecuencias trigramas
        ArrayList<TrigramsOcurrence> frecTrigramas = TrigramsOcurrence.frequenciesMod26(textoCifrado);
        i = 0;
        datos = new Object[frecTrigramas.size()][6];
        String etiquetas3[] = {"Trigrama", "Frec"};
        for (TrigramsOcurrence frecuencia : frecTrigramas) {
            int[] aux = {frecuencia.getFirstLetter(),
                frecuencia.getSecondLetter(), frecuencia.getThirdLetter()};
            int frec = frecuencia.getFrequency();
            datos[i][0] = Code.decodeMod26(aux);
            datos[i][1] = frec;
            i++;
        }
        pintarFrecuencias(tablaResultadosAffineTrigramas, 6, datos, etiquetas3);
        ArrayList<AffineCipher> textosPlanos = AffineCipher.cryptoAnalysis(textoCifrado);
        String claves = "";
        int contador = 0;
        for (AffineCipher affine : textosPlanos.subList(0, 25)) {
            int aditivo = affine.getAdditiveKey();
            int multiplicativo = affine.getMultiplicativeKey();
            if (contador < 7) {
                claves = claves.concat("(" + String.valueOf(multiplicativo)
                        + "," + String.valueOf(aditivo) + ") ");
                contador++;
            }
            String textoAffine = affine.getText();
            textoPlano = textoPlano.concat("Clave: ( "
                    + String.valueOf(multiplicativo) + " , "
                    + String.valueOf(aditivo) + " )\n");
            textoPlano = textoPlano.concat(textoAffine.concat("\n\n"));
        }
        textoMasProbablesClavesAffineR.setText(claves);
        cajaTextoPlano.setText(textoPlano);
        cajaTextoCifrado.setText(textoCifrado);
    }

    private void criptoAnalisisVigenere(String textoCifrado) {
        try {
            cajaTextoPlano.setText("");
            ArrayList<VigenereCipher> textoPlanoArray = VigenereCipher.cryptoAnalysis(textoCifrado);
            VigenereCipher textoPlano = textoPlanoArray.get(0);
            cajaTextoPlano.setText(textoPlano.getText());
            cajaTextoCifrado.setText(textoCifrado);
            textoResultadoVigenereClave.setText(textoPlano.getKey().toUpperCase());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "El texto cifrado no aporta suficiente información para calcular el índice de coincidencia",
                    "Error al realizar el criptoanálisis",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fuerzaBrutaDESS(String textoCifrado) {
        textoCifrado = Code.removeCharactersOutOfHexa(cajaTextoCifrado.getText());

        if (textoCifrado.length() % 2 != 0) {
            Random rand = new Random();
            char randChar = (char) (rand.nextInt(6) + 65);
            textoCifrado = textoCifrado.concat(String.valueOf(randChar));
        }
        String[][] p = SimplifiedDESCipher.bruteForce(textoCifrado);
        String textoPlano = "";
        for (int x = 0; x < 1024; x++) {
            textoPlano = textoPlano.concat("Clave: ".concat(p[x][0]).concat(
                    "\n"));
            textoPlano = textoPlano.concat(p[x][1].concat("\n\n"));
        }
        cajaTextoPlano.setText(textoPlano);
        cajaTextoCifrado.setText(textoCifrado);
    }

    private void descifrarTSDES(String textoCifrado) {
        try {
            String clave = "";
            String textoHexa = "";
            String textoPlano = "";

            Random rand = new Random();
            while (textoCifrado.length() % 2 != 0) {
                char letra = (char) (rand.nextInt(6) + 65);
                textoCifrado = textoCifrado.concat(String.valueOf(letra));
            }

            clave = claveTripleDESS.getText();
            String stringTemp = "";
            if (clave.length() == 30) {
                for (int x = 0; x < textoCifrado.length(); x = x + 2) {
                    textoHexa = textoCifrado.substring(x, x + 2);
                    stringTemp = SimplifiedDESCipher.encryptDecrypt(textoHexa,
                            clave.substring(20, 30), false);
                    stringTemp = SimplifiedDESCipher.encryptDecrypt(stringTemp,
                            clave.substring(10, 20), true);
                    textoPlano = textoPlano.concat(SimplifiedDESCipher.encryptDecrypt(stringTemp, clave.substring(0, 10),
                            false));
                }
                cajaTextoPlano.setText(cpcommonmethods.HexTools.fromHexStringToASCIIString(textoPlano));
                cajaTextoCifrado.setText(textoCifrado);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 30 dígitos binarios o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 30 dígitos binarios o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.print(e.toString());
        }

    }

    private void descifrarDES(String textoCifrado) {

        try {
            String claveHexa = "";
            String textoHexa = "";
            String textoPlano = "";

            Random rand = new Random();
            while (textoCifrado.length() % 16 != 0) {
                char letra = (char) (rand.nextInt(6) + 65);
                textoCifrado = textoCifrado.concat(String.valueOf(letra));
            }

            claveHexa = claveDES.getText();
            if (claveHexa.length() == 16) {
                for (int x = 0; x < textoCifrado.length(); x = x + 16) {
                    textoHexa = textoCifrado.substring(x, x + 16);
                    textoPlano = textoPlano.concat(DESCipher.encryptDecrypt(
                            textoHexa, claveHexa, false));
                }
                cajaTextoPlano.setText(cpcommonmethods.HexTools.fromHexStringToASCIIString(textoPlano));
                cajaTextoCifrado.setText(textoCifrado);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 16 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 16 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.print(e.toString());
        }
    }

    private void descifrarTDES(String textoCifrado) {
        try {
            String claveHexa = "";
            String textoHexa = "";
            String textoPlano = "";

            Random rand = new Random();
            while (textoCifrado.length() % 16 != 0) {
                char letra = (char) (rand.nextInt(6) + 65);
                textoCifrado = textoCifrado.concat(String.valueOf(letra));
            }

            claveHexa = claveTDES.getText();
            if (claveHexa.length() == 48) {
                String stringTemp = "";
                for (int x = 0; x < textoCifrado.length(); x = x + 16) {
                    textoHexa = textoCifrado.substring(x, x + 16);

                    stringTemp = DESCipher.encryptDecrypt(textoHexa,
                            claveHexa.substring(32, 48), false);
                    stringTemp = DESCipher.encryptDecrypt(stringTemp,
                            claveHexa.substring(16, 32), true);
                    textoPlano = textoPlano.concat(DESCipher.encryptDecrypt(
                            stringTemp, claveHexa.substring(0, 16), false));

                }
                cajaTextoPlano.setText(cpcommonmethods.HexTools.fromHexStringToASCIIString(textoPlano));
                cajaTextoCifrado.setText(textoCifrado);
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 48 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave esta compuesta por 48 dígitos hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.print(e.toString());
        }
    }

    private void descifrarAES(String textoCifrado) {
        String clave = claveAES.getText();
        cajaTextoCifrado.setText(textoCifrado);
        String textoPlano = "";
        try {
            if (clave.length() != 32) {
                throw new NumberFormatException();
            }
            textoPlano = AESCipher.longDecrypt(textoCifrado, clave);
            cajaTextoPlano.setText(textoPlano);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es válida\nAsegúrese que la clave está compuesta por 32 hexadecimales o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.toString());
        }
    }

    private void descifrarRSA(String textoCifrado) {
        try {
            String result;
            String n = cajaClaveRSAn.getText();
            String p = cajaClaveRSAp.getText();
            String q = cajaClaveRSAq.getText();
            String e = cajaClaveRSAe.getText();
            String d = cajaClaveRSAd.getText();

            if (tipoClaveRSAn.isSelected()) {
                tipoClaveRSAdActionPerformed(null);
                if (n.isEmpty()) {
                    throw new Exception(
                            "Es necesario ingresar el valor de n para descifrar el texto.");
                }
                if (d.isEmpty()) {
                    throw new Exception(
                            "Es necesario ingresar el valor de d para cifrar el texto.");
                }
                if (tipoOptimizacionRSA.isSelected()) {
                    result = BellareRogawayCipher.decrypt(textoCifrado, n, d);
                } else {
                    result = RSACipher.decrypt(d, n, textoCifrado);
                }
            } else {
                if (p.isEmpty() || q.isEmpty()) {
                    throw new Exception(
                            "Es necesario ingresar los valores de p y q para descifrar \nel texto.");
                }
                if (tipoClaveRSAe.isSelected()) {
                    tipoClaveRSAdActionPerformed(null);
                    if (e.isEmpty()) {
                        throw new Exception(
                                "Es necesario ingresar el valor de d para descifrar el texto.");
                    } else {
                        d = RSACipher.calculateInverse(e, p, q);
                    }
                } else {
                    if (d.isEmpty()) {
                        throw new Exception(
                                "Es necesario ingresar el valor de d para cifrar el texto.");
                    } else {
                        e = RSACipher.calculateInverse(d, p, q);
                    }
                }
                if (tipoOptimizacionRSA.isSelected()) {
                    result = BellareRogawayCipher.decrypt(textoCifrado, p, q, d);
                } else {
                    result = RSACipher.decrypt(p, q, d, textoCifrado);
                }
                cajaClaveRSAn.setText(RSACipher.calculateN(p, q));
                cajaClaveRSAd.setText(d);
                cajaClaveRSAe.setText(e);
            }

            cajaTextoPlano.setText(result);
            cajaTextoCifrado.setText(textoCifrado);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "El formato de la clave es inválido. Asegúrese de usar únicamente números en formato decimal.",
                    "Error al descifrar", JOptionPane.ERROR_MESSAGE);
        } catch (OutOfMemoryError e) {
            JOptionPane.showMessageDialog(this,
                    "Se ha excedido la capacidad de máquina.",
                    "Error al descifrar", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(),
                    "Error al descifrar", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void descifrarTablet(String textoCifrado) {
        String claveCadena = claveD1.getText();
        if (claveCadena.length() < 8 || claveCadena.length() > 15) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegúrese que la clave está compuesta por 8 a 15 letras y/o números o pruebe pulsando el botón\n          Generar una clave",
                    "Error al cifrar", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                cajaTextoPlano.setText(D1Cipher.decrypt(textoCifrado, claveCadena));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "La clave ingresada no es valida\nAsegúrese que la clave está compuesta por 8 a 15 letras y/o números o pruebe pulsando el botón\n          Generar una clave",
                        "Error al cifrar", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void botonCriptoanalisisActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonCriptoanalisisActionPerformed
        if (!cajaTextoCifrado.getText().isEmpty()) {
            cajaTextoPlano.setText("");

            String metodo = "";
            if (panelClasesCifrados.getSelectedIndex() == 0) {
                metodo = panelMetodosClasicos.getSelectedComponent().getName();
            } else if (panelClasesCifrados.getSelectedIndex() == 1) {
                metodo = panelMetodosBloque.getSelectedComponent().getName();
            } else {
                metodo = panelMetodosPublicos.getSelectedComponent().getName();
            }

            String textoCifrado = cajaTextoCifrado.getText();
            String textoCifradoMod26 = Code.removeCharactersOutOfMod26(
                    textoCifrado).toUpperCase();
            String textoCifradoMod189 = Code.removeCharactersOutOfMod189(textoCifrado);
            String textoCifradoDigitos = Code.removeCharactersOutOfDigits(textoCifrado);
            String textoCifradoHexa = Code.removeCharactersOutOfHexa(textoCifrado);

            if (metodo.equals("desplazamiento")) {
                fuerzaBrutaDesplazamiento(textoCifradoMod189);
            }
            if (metodo.equals("sustitucion")) {
                analisisFrecuenciasSustitucion(textoCifradoMod26);
            }
            if (metodo.equals("affine")) {
                criptoAnalisisAffine(textoCifradoMod26);
            }
            if (metodo.equals("vigenere")) {
                criptoAnalisisVigenere(textoCifradoMod26);
            }
            if (metodo.equals("hill")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El criptoanálisis para el metodo de Hill no esta disponible.",
                        "Error al realizar el criptoanálisis",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (metodo.equals("permutacion")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El criptoanálisis para el metodo de Permutación no esta disponible.",
                        "Error al realizar el criptoanálisis",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (metodo.equals("spn")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El criptoanálisis para el metodo SPN está disponible únicamente \n por aproximación lineal.",
                        "Error al realizar el criptoanálisis",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (metodo.equals("sdes")) {
                fuerzaBrutaDESS(textoCifradoHexa);
            }
            if (metodo.equals("tsdes")) {
                descifrarTSDES(textoCifradoHexa);
            }
            if (metodo.equals("des")) {
                descifrarDES(textoCifradoHexa);
            }
            if (metodo.equals("tdes")) {
                descifrarTDES(textoCifradoHexa);
            }
            if (metodo.equals("aes")) {
                descifrarAES(textoCifradoHexa);
            }
            if (metodo.equals("cbcmac")) {
                JOptionPane.showMessageDialog(
                        this,
                        "El criptoanálisis para el metodo CBCMAC no esta disponible.",
                        "Error al realizar el criptoanálisis",
                        JOptionPane.ERROR_MESSAGE);
            }
            if (metodo.equals("rsa")) {
                descifrarRSA(textoCifradoDigitos);
            }
            if (metodo.equals("tablet")) {
                descifrarTablet(textoCifrado.toLowerCase());
            }

            textoPlanoInicial = cajaTextoPlano.getText();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un texto cifrado para descifrarlo.",
                    "Error al realizar el criptoanálisis",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_botonCriptoanalisisActionPerformed

    private void pintarFrecuencias(javax.swing.JTable tabla, int columnas,
            Object[][] datos, String[] etiquetas) {
        switch (columnas) {
            case 2: {
                tabla.setModel(new javax.swing.table.DefaultTableModel(datos,
                        etiquetas) {

                    /**
                     *
                     */
                    private static final long serialVersionUID = 1L;
                    Class[] types = types2;

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                });
            }
            break;
            case 4: {
                tabla.setModel(new javax.swing.table.DefaultTableModel(datos,
                        etiquetas) {

                    /**
                     *
                     */
                    private static final long serialVersionUID = 1L;
                    Class[] types = types4;

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                });
            }
            break;
            case 6: {
                tabla.setModel(new javax.swing.table.DefaultTableModel(datos,
                        etiquetas) {

                    /**
                     *
                     */
                    private static final long serialVersionUID = 1L;
                    Class[] types = types6;

                    public Class getColumnClass(int columnIndex) {
                        return types[columnIndex];
                    }
                });
            }
            break;
        }

        centrarCeldas(tabla);
    }
    Class[] types2 = new Class[]{java.lang.String.class,
        java.lang.Integer.class};
    Class[] types4 = new Class[]{java.lang.String.class,
        java.lang.Integer.class, java.lang.String.class,
        java.lang.Integer.class};
    Class[] types6 = new Class[]{java.lang.String.class,
        java.lang.Integer.class, java.lang.String.class,
        java.lang.Integer.class, java.lang.String.class,
        java.lang.Integer.class};

    private void centrarCeldas(javax.swing.JTable tabla) {
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(SwingConstants.CENTER);
        for (int c = 0; c < tabla.getColumnCount(); c++) {
            tabla.getColumnModel().getColumn(c).setCellRenderer(tcr);
        }

    }

    private void botonSalirActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonSalirActionPerformed
        System.exit(0);
    }// GEN-LAST:event_botonSalirActionPerformed

    private void clavePermutacionKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_clavePermutacionKeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == '0') {
            evt.consume();
        } else {
            String clave = clavePermutacion.getText();
            if (clave.contains(c.toString())) {
                evt.consume();
            }
        }
    }// GEN-LAST:event_clavePermutacionKeyTyped

    private void botonClavePermutacionActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClavePermutacionActionPerformed
        Random rnd = new Random();
        int numero = 0;
        String clave = String.valueOf(rnd.nextInt(8) + 1);
        while (clave.length() != 9) {
            numero = rnd.nextInt(9) + 1;
            if (numero != 0 && !clave.contains(String.valueOf(numero))) {
                clave = clave.concat(String.valueOf(numero));
            }
        }
        clavePermutacion.setText(clave);
    }// GEN-LAST:event_botonClavePermutacionActionPerformed

    private void tipoClaveHill3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveHill3ActionPerformed
        claveHill3.setEditable(true);
        claveHill6.setEditable(true);
        claveHill7.setEditable(true);
        claveHill8.setEditable(true);
        claveHill9.setEditable(true);
    }// GEN-LAST:event_tipoClaveHill3ActionPerformed

    private void tipoClaveHill2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveHill2ActionPerformed
        claveHill3.setEditable(false);
        claveHill6.setEditable(false);
        claveHill7.setEditable(false);
        claveHill8.setEditable(false);
        claveHill9.setEditable(false);
        claveHill3.setText("");
        claveHill6.setText("");
        claveHill7.setText("");
        claveHill8.setText("");
        claveHill9.setText("");
    }// GEN-LAST:event_tipoClaveHill2ActionPerformed

    private void claveHill9KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill9KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill9.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill9KeyTyped

    private void claveHill8KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill8KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill8.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill8KeyTyped

    private void claveHill7KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill7KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill7.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill7KeyTyped

    private void claveHill6KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill6KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill6.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill6KeyTyped

    private void claveHill5KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill5KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill5.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill5KeyTyped

    private void claveHill4KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill4KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill4.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill4KeyTyped

    private void claveHill3KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill3KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill3.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill3KeyTyped

    private void claveHill2KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill2KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill2.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill2KeyTyped

    private void claveHill1KeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveHill1KeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)
                || Integer.parseInt(claveHill1.getText().concat(c.toString())) > 25) {
            evt.consume();
        }
    }// GEN-LAST:event_claveHill1KeyTyped

    private void botonClaveHillActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveHillActionPerformed
        Random rnd = new Random();
        if (tipoClaveHill2.isSelected()) {
            int det = 2;
            while (mcd(det, 26) != 1) {
                double[][] valores = {{rnd.nextInt(25), rnd.nextInt(25)},
                    {rnd.nextInt(25), rnd.nextInt(25)}};
                claveHill1.setText(String.valueOf((int) valores[0][0]));
                claveHill2.setText(String.valueOf((int) valores[0][1]));
                claveHill4.setText(String.valueOf((int) valores[1][0]));
                claveHill5.setText(String.valueOf((int) valores[1][1]));
                Matrix clave = new Matrix(valores);
                det = (int) Math.round(clave.det());
            }
        } else {
            int det = 2;
            while (mcd(det, 26) != 1) {
                double[][] valores = {
                    {rnd.nextInt(25), rnd.nextInt(25), rnd.nextInt(25)},
                    {rnd.nextInt(25), rnd.nextInt(25), rnd.nextInt(25)},
                    {rnd.nextInt(25), rnd.nextInt(25), rnd.nextInt(25)}};
                claveHill1.setText(String.valueOf((int) valores[0][0]));
                claveHill2.setText(String.valueOf((int) valores[0][1]));
                claveHill3.setText(String.valueOf((int) valores[0][2]));
                claveHill4.setText(String.valueOf((int) valores[1][0]));
                claveHill5.setText(String.valueOf((int) valores[1][1]));
                claveHill6.setText(String.valueOf((int) valores[1][2]));
                claveHill7.setText(String.valueOf((int) valores[2][0]));
                claveHill8.setText(String.valueOf((int) valores[2][1]));
                claveHill9.setText(String.valueOf((int) valores[2][2]));
                Matrix clave = new Matrix(valores);
                det = (int) Math.round(clave.det());
            }
        }
    }// GEN-LAST:event_botonClaveHillActionPerformed

    private void claveVigenereKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveVigenereKeyTyped
        char c = evt.getKeyChar();
        if ((!Character.isLetter(c) && !Character.isWhitespace(c)) || c == 'ñ'
                || c == 'Ñ' || claveVigenere.getText().length() == 8) {
            evt.consume();
        } else {
            if (Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
                claveVigenere.setText(claveVigenere.getText().concat(
                        String.valueOf(c)));
                evt.consume();
            }
        }
    }// GEN-LAST:event_claveVigenereKeyTyped

    private void botonClaveVigenereActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveVigenereActionPerformed
        Random rnd = new Random();
        String clave = "";
        for (int x = 0; x < 8; x++) {
            char claveChar = (char) (rnd.nextInt(25) + 65);
            clave = clave.concat(String.valueOf(claveChar));
        }
        claveVigenere.setText(clave);
    }// GEN-LAST:event_botonClaveVigenereActionPerformed

    private void claveAffineBKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveAffineBKeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        } else {
            if (Integer.parseInt(claveAffineB.getText().concat(
                    String.valueOf(c))) > 25) {
                evt.consume();
            }
        }
    }// GEN-LAST:event_claveAffineBKeyTyped

    private void claveAffineAKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveAffineAKeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        } else {
            if (Integer.parseInt(claveAffineA.getText().concat(
                    String.valueOf(c))) > 25) {
                evt.consume();
            }
        }
    }// GEN-LAST:event_claveAffineAKeyTyped

    private void botonClaveAffineActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveAffineActionPerformed
        Random rnd = new Random();
        int clave = 2;
        while (mcd(clave, 26) != 1) {
            clave = rnd.nextInt(25);
        }
        claveAffineA.setText(String.valueOf(clave));
        claveAffineB.setText(String.valueOf(rnd.nextInt(25)));
    }// GEN-LAST:event_botonClaveAffineActionPerformed

    private void claveSustitucionKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isLetter(c) || c == 'ñ' || c == 'Ñ'
                || claveSustitucion.getText().length() == 26) {
            evt.consume();
        } else {
            String clave = claveSustitucion.getText();
            if (clave.contains(String.valueOf(c).toUpperCase())) {
                evt.consume();
            } else {
                if (Character.isLowerCase(c)) {
                    c = Character.toUpperCase(c);
                    claveSustitucion.setText(claveSustitucion.getText().concat(
                            String.valueOf(c)));
                    evt.consume();
                }
            }
        }
    }// GEN-LAST:event_claveSustitucionKeyTyped

    private void botonClaveSustitucionActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveSustitucionActionPerformed
        Random rnd = new Random();
        String clave = "";
        while (clave.length() != 26) {
            char claveChar = (char) (rnd.nextInt(26) + 65);
            if (!clave.contains(String.valueOf(claveChar))) {
                clave = clave.concat(String.valueOf(claveChar));
            }
        }
        claveSustitucion.setText(clave);
    }// GEN-LAST:event_botonClaveSustitucionActionPerformed

    private void tipoClaveDesplazamientoCaracterActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveDesplazamientoCaracterActionPerformed
        claveDesplazamientoNumero.setEnabled(false);
        claveDesplazamientoCaracter.setEnabled(true);
    }// GEN-LAST:event_tipoClaveDesplazamientoCaracterActionPerformed

    private void claveDesplazamientoCaracterKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveDesplazamientoCaracterKeyTyped
        Character c = evt.getKeyChar();
        if (c < 32 || (c > 126 && c < 161) || c > 255) {
            evt.consume();
        } else {
            claveDesplazamientoCaracter.setText("");
            int[] encode = Code.encodeMod189(String.valueOf(evt.getKeyChar()));
            claveDesplazamientoNumero.setText(String.valueOf(encode[0]));

            int clave = Integer.parseInt(claveDesplazamientoNumero.getText());
            String textoCifrado = ShiftCipher.encryptMod189(
                    muestraDesplazamientoDe.getText(), clave);
            muestraDesplazamientoA.setText(textoCifrado);
        }
    }// GEN-LAST:event_claveDesplazamientoCaracterKeyTyped

    private void tipoClaveDesplazamientoNumeroActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveDesplazamientoNumeroActionPerformed
        claveDesplazamientoCaracter.setEnabled(false);
        claveDesplazamientoNumero.setEnabled(true);
    }// GEN-LAST:event_tipoClaveDesplazamientoNumeroActionPerformed

    private void claveDesplazamientoNumeroKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveDesplazamientoNumeroKeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        } else {
            String decode = "A";
            if (!claveDesplazamientoNumero.getText().isEmpty()) {
                int clave = Integer.parseInt(claveDesplazamientoNumero.getText());
                if (clave == 0) {
                    claveDesplazamientoNumero.setText("");
                }

                clave = Integer.parseInt(claveDesplazamientoNumero.getText().concat(c.toString()));
                if (clave > 188) {
                    claveDesplazamientoNumero.setText("");
                }

                clave = Integer.parseInt(claveDesplazamientoNumero.getText().concat(c.toString()));
                int decodeNumber[] = {Integer.parseInt(claveDesplazamientoNumero.getText().concat(
                    c.toString()))};
                decode = Code.decodeMod189(decodeNumber);

                muestraDesplazamientoA.setText(ShiftCipher.encryptMod189(
                        muestraDesplazamientoDe.getText(), clave));
            } else {
                int decodeNumber[] = {Integer.parseInt(c.toString())};
                decode = Code.decodeMod189(decodeNumber);
                muestraDesplazamientoA.setText(ShiftCipher.encryptMod189(
                        muestraDesplazamientoDe.getText(), decodeNumber[0]));
            }

            claveDesplazamientoCaracter.setText(decode);
        }
    }// GEN-LAST:event_claveDesplazamientoNumeroKeyTyped

    private void botonClaveDesplazamientoActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveDesplazamientoActionPerformed
        Random rnd = new Random();
        int clave = rnd.nextInt(188);
        claveDesplazamientoNumero.setText(String.valueOf(clave));
        muestraDesplazamientoA.setText(ShiftCipher.encryptMod189(
                muestraDesplazamientoDe.getText(), clave));

        int decodeNumber[] = {clave};
        String decode = Code.decodeMod189(decodeNumber);
        claveDesplazamientoCaracter.setText(decode);
    }// GEN-LAST:event_botonClaveDesplazamientoActionPerformed

    private void clavePermutacionAlternativaActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clavePermutacionAlternativaActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_clavePermutacionAlternativaActionPerformed

    private void panelMetodoPermutacionFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_panelMetodoPermutacionFocusGained
        JOptionPane.showMessageDialog(
                this,
                "Ingrese un texto cifrado para poder realizar el criptoanálisis",
                "Error al realizar el criptoanálisis",
                JOptionPane.ERROR_MESSAGE);
    }// GEN-LAST:event_panelMetodoPermutacionFocusGained

    private void botonAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonAcercaDeActionPerformed
        JOptionPane.showMessageDialog(this, "Realizado por:\n"
                + "\nAgustín Moreno Cañadas - amorenoca@unal.edu.co"
                + "\nDavid Montaño Fetecua - damontanofe@unal.edu.co"
                + "\nLaura Moreno Cubillos - lvmorenoc@unal.edu.co"
                + "\nChristian Rodríguez Bustos - carodriguezb@unal.edu.co\n"
                + "\nUniversidad Nacional de Colombia" + "\nSede Bogotá\n\n",
                "Acerca de...", JOptionPane.INFORMATION_MESSAGE);
    }// GEN-LAST:event_botonAcercaDeActionPerformed

    public class TxTFilter extends javax.swing.filechooser.FileFilter {

        final static String txt = "txt";

        /** Creates a new instance of XMLFilter */
        public TxTFilter() {
        }

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                String extension = s.substring(i + 1).toLowerCase();
                if (txt.equals(extension)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        public String getDescription() {
            return "archivos .txt";
        }
    }

    public class CSVFilter extends javax.swing.filechooser.FileFilter {

        final static String csv = "txt";

        /** Creates a new instance of XMLFilter */
        public CSVFilter() {
        }

        public boolean accept(File f) {
            if (f.isDirectory()) {
                return true;
            }
            String s = f.getName();
            int i = s.lastIndexOf('.');

            if (i > 0 && i < s.length() - 1) {
                String extension = s.substring(i + 1).toLowerCase();
                if (csv.equals(extension)) {
                    return true;
                } else {
                    return false;
                }
            }
            return false;
        }

        public String getDescription() {
            return "archivos .csv";
        }
    }

    private void botonGuardarCifradoActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonGuardarCifradoActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new TxTFilter());
        chooser.showSaveDialog(this);
        File archivo = chooser.getSelectedFile();
        try {
            PrintWriter writer = new PrintWriter(archivo);
            writer.print(cajaTextoCifrado.getText());
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }// GEN-LAST:event_botonGuardarCifradoActionPerformed

    private void botonAbrirPlanoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonAbrirPlanoActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new TxTFilter());
        chooser.showOpenDialog(this);
        File archivo = chooser.getSelectedFile();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String linea = "";
            String texto = "";
            while ((linea = reader.readLine()) != null) {
                texto = texto.concat(linea).concat("\n");
            }
            reader.close();
            cajaTextoPlano.setText(texto);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }// GEN-LAST:event_botonAbrirPlanoActionPerformed

    private void botonLimpiarCifradoActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarCifradoActionPerformed
        cajaTextoCifrado.setText("");
    }// GEN-LAST:event_botonLimpiarCifradoActionPerformed

    private void botonGuardarPlanoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonGuardarPlanoActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new TxTFilter());
        chooser.showSaveDialog(this);
        File archivo = chooser.getSelectedFile();
        try {
            PrintWriter writer = new PrintWriter(archivo);
            writer.print(cajaTextoPlano.getText());
            writer.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }// GEN-LAST:event_botonGuardarPlanoActionPerformed

    private void botonLimpiarPlano1ActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarPlano1ActionPerformed
        cajaTextoPlano.setText("");
    }// GEN-LAST:event_botonLimpiarPlano1ActionPerformed

    private void botonLimpiarPermutacionActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarPermutacionActionPerformed
        clavePermutacion.setText("");
        clavePermutacionAlternativa.setSelected(false);
    }// GEN-LAST:event_botonLimpiarPermutacionActionPerformed

    private void botonLimpiarHillActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarHillActionPerformed
        claveHill1.setText("");
        claveHill2.setText("");
        claveHill3.setText("");
        claveHill4.setText("");
        claveHill5.setText("");
        claveHill6.setText("");
        claveHill7.setText("");
        claveHill8.setText("");
        claveHill9.setText("");
        claveHill3.setEditable(false);
        claveHill6.setEditable(false);
        claveHill7.setEditable(false);
        claveHill8.setEditable(false);
        claveHill9.setEditable(false);
        tipoClaveHill2.setSelected(true);
    }// GEN-LAST:event_botonLimpiarHillActionPerformed

    private void botonLimpiarVigenereActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarVigenereActionPerformed
        claveVigenere.setText("");
    }// GEN-LAST:event_botonLimpiarVigenereActionPerformed

    private void botonLimpiarAffineActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarAffineActionPerformed
        claveAffineA.setText("");
        claveAffineB.setText("");
    }// GEN-LAST:event_botonLimpiarAffineActionPerformed

    private void botonLimpiarSustitucionActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarSustitucionActionPerformed
        claveSustitucion.setText("");
    }// GEN-LAST:event_botonLimpiarSustitucionActionPerformed

    private void botonLimpiarDesplazamientoActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarDesplazamientoActionPerformed
        claveDesplazamientoCaracter.setText("");
        claveDesplazamientoCaracter.setEnabled(false);
        claveDesplazamientoNumero.setEnabled(true);
        claveDesplazamientoNumero.setText("");
        tipoClaveDesplazamientoNumero.setSelected(true);
        muestraDesplazamientoA.setText("");
    }// GEN-LAST:event_botonLimpiarDesplazamientoActionPerformed

    public void sustitucionTodos(java.awt.event.KeyEvent evt, int n) {
        Character c = evt.getKeyChar();
        if (!Character.isLetter(c) || c == 'ñ' || c == 'Ñ'
                || arregloSustitucion[n].getText().length() == 1) {
            evt.consume();
        } else {
            if (Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
                arregloSustitucion[n].setText(arregloSustitucion[n].getText().concat(String.valueOf(c)));
                evt.consume();
            }
            for (int i = 0; i < 26; i++) {
                if (n != i) {
                    if (arregloSustitucion[i].getText().equals(
                            String.valueOf(c))) {
                        arregloSustitucion[i].setText("");
                        break;
                    }
                }
            }
            textoMasProbablesClavesSustitucionR.setText("");
            for (int i = 0; i < 26; i++) {
                if (!arregloSustitucion[i].getText().isEmpty()) {
                    textoMasProbablesClavesSustitucionR.setText(textoMasProbablesClavesSustitucionR.getText().concat(
                            arregloSustitucion[i].getText()));
                } else {
                    textoMasProbablesClavesSustitucionR.setText(textoMasProbablesClavesSustitucionR.getText().concat("-"));
                }
            }

            reEscribirTextoPlanoCambiar();
        }
    }

    public void reEscribirTextoPlanoCambiar() {
        String textoPlano = cajaTextoCifrado.getText();
        for (int i = 0; i < 26; i++) {
            String cambio = arregloSustitucion[i].getText();
            if (!cambio.equals("")) {
                textoPlano = textoPlano.replace(cambio,
                        Code.decodeMod26(new int[]{i}));
            }
        }
        cajaTextoPlano.setText(textoPlano);
    }

    private void claveSustitucionAKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionAKeyTyped
        this.sustitucionTodos(evt, 0);
    }// GEN-LAST:event_claveSustitucionAKeyTyped

    private void claveSustitucionBKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionBKeyTyped
        this.sustitucionTodos(evt, 1);
    }// GEN-LAST:event_claveSustitucionBKeyTyped

    private void claveSustitucionCKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionCKeyTyped
        this.sustitucionTodos(evt, 2);
    }// GEN-LAST:event_claveSustitucionCKeyTyped

    private void claveSustitucionDKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionDKeyTyped
        this.sustitucionTodos(evt, 3);
    }// GEN-LAST:event_claveSustitucionDKeyTyped

    private void claveSustitucionEKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionEKeyTyped
        this.sustitucionTodos(evt, 4);
    }// GEN-LAST:event_claveSustitucionEKeyTyped

    private void claveSustitucionGKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionGKeyTyped
        this.sustitucionTodos(evt, 6);
    }// GEN-LAST:event_claveSustitucionGKeyTyped

    private void claveSustitucionFKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionFKeyTyped
        this.sustitucionTodos(evt, 5);
    }// GEN-LAST:event_claveSustitucionFKeyTyped

    private void claveSustitucionIKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionIKeyTyped
        this.sustitucionTodos(evt, 8);
    }// GEN-LAST:event_claveSustitucionIKeyTyped

    private void claveSustitucionHKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionHKeyTyped
        this.sustitucionTodos(evt, 7);
    }// GEN-LAST:event_claveSustitucionHKeyTyped

    private void claveSustitucionJKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionJKeyTyped
        this.sustitucionTodos(evt, 9);
    }// GEN-LAST:event_claveSustitucionJKeyTyped

    private void claveSustitucionKKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionKKeyTyped
        this.sustitucionTodos(evt, 10);
    }// GEN-LAST:event_claveSustitucionKKeyTyped

    private void claveSustitucionLKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionLKeyTyped
        this.sustitucionTodos(evt, 11);
    }// GEN-LAST:event_claveSustitucionLKeyTyped

    private void claveSustitucionMKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionMKeyTyped
        this.sustitucionTodos(evt, 12);
    }// GEN-LAST:event_claveSustitucionMKeyTyped

    private void claveSustitucionNKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionNKeyTyped
        this.sustitucionTodos(evt, 13);
    }// GEN-LAST:event_claveSustitucionNKeyTyped

    private void claveSustitucionOKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionOKeyTyped
        this.sustitucionTodos(evt, 14);
    }// GEN-LAST:event_claveSustitucionOKeyTyped

    private void claveSustitucionPKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionPKeyTyped
        this.sustitucionTodos(evt, 15);
    }// GEN-LAST:event_claveSustitucionPKeyTyped

    private void claveSustitucionQKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionQKeyTyped
        this.sustitucionTodos(evt, 16);
    }// GEN-LAST:event_claveSustitucionQKeyTyped

    private void claveSustitucionRKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionRKeyTyped
        this.sustitucionTodos(evt, 17);
    }// GEN-LAST:event_claveSustitucionRKeyTyped

    private void claveSustitucionSKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionSKeyTyped
        this.sustitucionTodos(evt, 18);
    }// GEN-LAST:event_claveSustitucionSKeyTyped

    private void claveSustitucionTKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionTKeyTyped
        this.sustitucionTodos(evt, 19);
    }// GEN-LAST:event_claveSustitucionTKeyTyped

    private void claveSustitucionUKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionUKeyTyped
        this.sustitucionTodos(evt, 20);
    }// GEN-LAST:event_claveSustitucionUKeyTyped

    private void claveSustitucionVKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionVKeyTyped
        this.sustitucionTodos(evt, 21);
    }// GEN-LAST:event_claveSustitucionVKeyTyped

    private void claveSustitucionWKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionWKeyTyped
        this.sustitucionTodos(evt, 22);
    }// GEN-LAST:event_claveSustitucionWKeyTyped

    private void claveSustitucionXKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionXKeyTyped
        this.sustitucionTodos(evt, 23);
    }// GEN-LAST:event_claveSustitucionXKeyTyped

    private void claveSustitucionYKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionYKeyTyped
        this.sustitucionTodos(evt, 24);
    }// GEN-LAST:event_claveSustitucionYKeyTyped

    private void claveSustitucionZKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSustitucionZKeyTyped
        this.sustitucionTodos(evt, 25);
    }// GEN-LAST:event_claveSustitucionZKeyTyped

    private void botonLimpiarSustitucionManualActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarSustitucionManualActionPerformed
        if (cajaTextoCifrado.getText().isEmpty()) {
            textoPlanoInicial = "";
        } else {
            cajaTextoPlano.setText(textoPlanoInicial);
            textoMasProbablesClavesSustitucionR.setText("");
        }
        for (int i = 0; i < 26; i++) {
            arregloSustitucion[i].setText("");
        }
    }// GEN-LAST:event_botonLimpiarSustitucionManualActionPerformed

    private void clavePermutacionActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_clavePermutacionActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_clavePermutacionActionPerformed

    private void botonClaveSPNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveSPNActionPerformed
        // Clave aleatoria SPN
        Random rnd = new Random();
        String clave = "";
        for (int x = 0; x < 8; x++) {
            char claveChar = (char) (rnd.nextInt(16) + 65);
            clave = clave.concat(String.valueOf(claveChar));
        }
        claveSPN.setText(clave);

        char[] hexa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'};

        // Permutación aleatoria
        clave = "";
        while (clave.length() != 16) {
            char claveChar = hexa[rnd.nextInt(16)];
            // System.out.println("char  " + claveChar);
            if (!clave.contains(String.valueOf(claveChar))) {
                clave = clave.concat(String.valueOf(claveChar));
            }
        }
        permutacionSPN.setText(clave);

        // Sustitución aleatoria
        clave = "";
        while (clave.length() != 16) {
            char claveChar = hexa[rnd.nextInt(16)];
            // System.out.println("char  " + claveChar);
            if (!clave.contains(String.valueOf(claveChar))) {
                clave = clave.concat(String.valueOf(claveChar));
            }
        }
        sustitucionSPN.setText(clave);

        // Número de rondas aleatorio
        numeroRondasSPN.setText(String.valueOf(rnd.nextInt(4) + 1));
    }// GEN-LAST:event_botonClaveSPNActionPerformed

    private void botonLimpiarSPNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarSPNActionPerformed
        claveSPN.setText("");
        permutacionSPN.setText("");
        sustitucionSPN.setText("");
        numeroRondasSPN.setText("");
    }// GEN-LAST:event_botonLimpiarSPNActionPerformed

    private void claveSPNKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveSPNKeyTyped
        char c = evt.getKeyChar();
        if ((!Character.isLetter(c) && !Character.isWhitespace(c))
                || ((int) c >= 113) || c == 'ñ' || c == 'Ñ'
                || claveSPN.getText().length() == 8) {
            evt.consume();
        } else {
            if (Character.isLowerCase(c)) {
                c = Character.toUpperCase(c);
                claveSPN.setText(claveSPN.getText().concat(String.valueOf(c)));
                evt.consume();
            }
        }
    }// GEN-LAST:event_claveSPNKeyTyped

    private void permutacionSPNKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_permutacionSPNKeyTyped
        char c = evt.getKeyChar();
        c = String.valueOf(c).toUpperCase().charAt(0);

        if (c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E'
                && c != 'F' && !Character.isDigit(c)
                || permutacionSPN.getText().length() == 16) {
            evt.consume();
        } else {
            String clave = permutacionSPN.getText();
            if (clave.contains(String.valueOf(c).toUpperCase())
                    || clave.contains(String.valueOf(c).toLowerCase())) {
                evt.consume();
            } else {
                if (Character.isLowerCase(c)) {
                    c = Character.toUpperCase(c);
                    permutacionSPN.setText(permutacionSPN.getText().concat(
                            String.valueOf(c)));
                    evt.consume();
                }
            }
        }
    }// GEN-LAST:event_permutacionSPNKeyTyped

    private void sustitucionSPNKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_sustitucionSPNKeyTyped
        char c = evt.getKeyChar();
        c = String.valueOf(c).toUpperCase().charAt(0);
        if (c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E'
                && c != 'F' && !Character.isDigit(c)
                || sustitucionSPN.getText().length() == 16) {
            evt.consume();
        } else {
            String clave = sustitucionSPN.getText();
            if (clave.contains(String.valueOf(c).toUpperCase())
                    || clave.contains(String.valueOf(c).toLowerCase())) {
                evt.consume();
            } else {
                if (Character.isLowerCase(c)) {
                    c = Character.toUpperCase(c);
                    sustitucionSPN.setText(sustitucionSPN.getText().concat(
                            String.valueOf(c)));
                    evt.consume();
                }
            }
        }
    }// GEN-LAST:event_sustitucionSPNKeyTyped

    private void numeroRondasSPNKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_numeroRondasSPNKeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        } else {
            if (Integer.parseInt(numeroRondasSPN.getText().concat(
                    String.valueOf(c))) > 4
                    || Integer.parseInt(numeroRondasSPN.getText().concat(
                    String.valueOf(c))) < 1) {
                evt.consume();
            }
        }
    }// GEN-LAST:event_numeroRondasSPNKeyTyped

    private void buscarParejasActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buscarParejasActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new TxTFilter());
        chooser.showOpenDialog(this);
        File archivo = chooser.getSelectedFile();
        try {
            rutaParejas.setText(archivo.getPath());
        } catch (Exception ex) {
            System.out.println("Carga cancelada");
        }
    }// GEN-LAST:event_buscarParejasActionPerformed

    private void calcularL1L3ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_calcularL1L3ActionPerformed
        File archivo = new File(rutaParejas.getText());
        if (archivo.isFile()) {
            try {
                boolean format = true;
                BufferedReader reader = new BufferedReader(new FileReader(
                        archivo));
                Vector<String> cadenas = new Vector<String>();
                String linea;

                while ((linea = reader.readLine()) != null) {
                    if (linea.length() != 9
                            || !linea.matches("[a-pA-P]{4} {1}[a-pA-P]{4}")) {
                        format = false;
                        break;
                    }
                    cadenas.add(linea);
                }

                if (format) {
                    String[][] matrix = new String[cadenas.size()][2];
                    for (int i = 0; i < cadenas.size(); i++) {
                        matrix[i][0] = cadenas.get(i).substring(0, 4);
                        matrix[i][1] = cadenas.get(i).substring(5, 9);
                    }

                    char[] sustitucionChar = sustitucionSPN.getText().toCharArray();
                    int[] sustitucion = new int[16];
                    for (int x = 0; x < 16; x++) {
                        sustitucion[Integer.parseInt(
                                String.valueOf(sustitucionChar[x]), 16)] = x;
                    }

                    int[] maxKey = SubstitutionPermutationNetworkCipher.linearAttack(matrix, sustitucion);
                    String clave = Code.decodeMod26(maxKey).toUpperCase();
                    L1L2.setText("( " + clave.substring(0, 1) + " , "
                            + clave.substring(1, 2) + " )");
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "El formato del archivo es inválido.\nAsegurese que cada pareja texto plano - texto cifrado del archivo esté separada por un espacio, \n y que cada texto tenga 4 caracteres entre a y p. ",
                            "Error al realizar la aproximación lineal",
                            JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "Ruta de archivo inválida.\nAsegurese de especificar un archivo existente. ",
                    "Error al realizar la aproximación lineal",
                    JOptionPane.ERROR_MESSAGE);
        }
    }// GEN-LAST:event_calcularL1L3ActionPerformed

    private void botonMuestrasSPNActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonMuestrasSPNActionPerformed
        String clave = claveSPN.getText();
        if (clave.length() != 8) {
            JOptionPane.showMessageDialog(
                    this,
                    "La clave ingresada no es valida\nAsegurese que la clave es una palabra de 8 caracteres o pruebe pulsando el botón\n          Generar una clave",
                    "Error al generar parejas",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int nr = Integer.parseInt(numeroRondasSPN.getText());
                if (nr < 1 || nr > 4) {
                    JOptionPane.showMessageDialog(
                            this,
                            "El número de rondas es inválido\nAsegurese que el numero de rondas sea mayor a 0 y menor a 16 o pruebe pulsando el botón\n          Generar una clave",
                            "Error al generar parejas",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    if (permutacionSPN.getText().length() != 16
                            || sustitucionSPN.getText().length() != 16) {
                        JOptionPane.showMessageDialog(
                                this,
                                "Las funciones de permutación o sustitución son inválidas\nAsegurese que sean secuencias de 0 a F en cualquier orden sin repetir ningun valor o pruebe pulsando el botón\n          Generar una clave",
                                "Error al generar parejas",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        int n = Integer.parseInt(nParejas.getText());
                        if (n > 65000 || n < 1) {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "El número de parejas es inválido. \nLa cantidad n de parejas debe ser igual o mayor que 1 y menor que 65000\n",
                                    "Error al generar parejas",
                                    JOptionPane.ERROR_MESSAGE);
                        } else {
                            char[] permutacionChar = permutacionSPN.getText().toCharArray();
                            char[] sustitucionChar = sustitucionSPN.getText().toCharArray();
                            int[] permutacion = new int[16];
                            int[] sustitucion = new int[16];
                            for (int x = 0; x < 16; x++) {
                                permutacion[x] = (Integer.parseInt(
                                        String.valueOf(permutacionChar[x]), 16)) + 1;
                                sustitucion[x] = Integer.parseInt(
                                        String.valueOf(sustitucionChar[x]), 16);
                            }
                            try {
                                JFileChooser chooser = new JFileChooser();
                                chooser.addChoosableFileFilter(new TxTFilter());
                                chooser.showSaveDialog(this);
                                File archivo = chooser.getSelectedFile();
                                FileWriter fw = null;
                                try {
                                    fw = new FileWriter(archivo);
                                    String matrix[][] = SubstitutionPermutationNetworkCipher.generateSamples(sustitucion,
                                            permutacion, clave, nr, n);
                                    for (int i = 0; i < n; i++) {
                                        fw.write(matrix[i][0] + " "
                                                + matrix[i][1] + "\n");
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                } finally {
                                    if (fw != null) {
                                        try {
                                            fw.close();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        fw = null;
                                    }
                                }
                            } catch (Exception e) {
                                System.out.println("Operación cancelada");
                            }
                        }
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        this,
                        "El número de rondas es inválido\nAsegurese que el numero de rondas sea mayor a 0 y menor a 25 o pruebe pulsando el botón\n          Generar una clave",
                        "Error al generar parejas",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }// GEN-LAST:event_botonMuestrasSPNActionPerformed

    private void nParejasKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_nParejasKeyTyped
        Character c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        } else {
            if (Integer.parseInt(nParejas.getText().concat(String.valueOf(c))) < 1
                    || Integer.parseInt(nParejas.getText().concat(
                    String.valueOf(c))) > 65000) {
                evt.consume();
            }
        }
    }// GEN-LAST:event_nParejasKeyTyped

    private void botonClaveDESSActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveDESSActionPerformed
        Random rnd = new Random();
        String clave = "";
        while (clave.length() != 10) {
            int claveBit = rnd.nextInt(2);
            clave = clave.concat(String.valueOf(claveBit));
        }
        claveDESS.setText(clave);
    }// GEN-LAST:event_botonClaveDESSActionPerformed

    private void botonLimpiarDESSActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarDESSActionPerformed
        claveDESS.setText("");
    }// GEN-LAST:event_botonLimpiarDESSActionPerformed

    private void claveDESSKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveDESSKeyTyped
        char c = evt.getKeyChar();
        if ((c != '1' && c != '0') || claveDESS.getText().length() == 10) {
            evt.consume();
        }
    }// GEN-LAST:event_claveDESSKeyTyped

    private void botonClaveAESActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveAESActionPerformed
        Random rnd = new Random();
        char[] hexa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'};
        String clave = "";
        clave = "";
        while (clave.length() != 32) {
            char claveChar = hexa[rnd.nextInt(16)];
            clave = clave.concat(String.valueOf(claveChar));
        }
        claveAES.setText(clave);
    }// GEN-LAST:event_botonClaveAESActionPerformed

    private void botonLimpiarAESActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarAESActionPerformed
        claveAES.setText("");
    }// GEN-LAST:event_botonLimpiarAESActionPerformed

    private void claveAESKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveAESKeyTyped
        char c = evt.getKeyChar();
        c = String.valueOf(c).toUpperCase().charAt(0);

        if (c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E'
                && c != 'F' && !Character.isDigit(c)
                || permutacionSPN.getText().length() == 32) {
            evt.consume();
        } else {
            String clave = claveAES.getText();
            if (clave.contains(String.valueOf(c).toUpperCase())
                    || clave.contains(String.valueOf(c).toLowerCase())) {
                evt.consume();
            } else {
                if (Character.isLowerCase(c)) {
                    c = Character.toUpperCase(c);
                    claveAES.setText(claveAES.getText().concat(
                            String.valueOf(c)));
                    evt.consume();
                }
            }
        }
    }// GEN-LAST:event_claveAESKeyTyped

    private void botonClaveCBCMACActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveCBCMACActionPerformed
        Random rnd = new Random();
        char[] hexa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'};
        String clave = "";
        clave = "";
        while (clave.length() != 32) {
            char claveChar = hexa[rnd.nextInt(16)];
            clave = clave.concat(String.valueOf(claveChar));
        }
        claveCBCMAC.setText(clave);
    }// GEN-LAST:event_botonClaveCBCMACActionPerformed

    private void botonLimpiarCBCMACActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarCBCMACActionPerformed
        claveAES.setText("");
    }// GEN-LAST:event_botonLimpiarCBCMACActionPerformed

    private void claveCBCMACKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveCBCMACKeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_claveCBCMACKeyTyped

    private void tipoClaveRSAnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveRSAnActionPerformed
        cajaClaveRSAp.setEnabled(false);
        cajaClaveRSAq.setEnabled(false);
        cajaClaveRSAn.setEnabled(true);
        cajaClaveRSAn.setEnabled(true);
    }// GEN-LAST:event_tipoClaveRSAnActionPerformed

    private void tipoClaveRSApqActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveRSApqActionPerformed
        cajaClaveRSAn.setEnabled(false);
        cajaClaveRSAn.setEnabled(false);
        cajaClaveRSAp.setEnabled(true);
        cajaClaveRSAq.setEnabled(true);
    }// GEN-LAST:event_tipoClaveRSApqActionPerformed

    private void tipoClaveRSAdActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveRSAdActionPerformed
        cajaClaveRSAe.setEnabled(false);
        cajaClaveRSAd.setEnabled(true);
        tipoClaveRSAd.setSelected(true);
        tipoClaveRSAe.setSelected(false);
    }// GEN-LAST:event_tipoClaveRSAdActionPerformed

    private void tipoClaveRSAeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoClaveRSAeActionPerformed
        cajaClaveRSAd.setEnabled(false);
        cajaClaveRSAe.setEnabled(true);
        tipoClaveRSAe.setSelected(true);
        tipoClaveRSAd.setSelected(false);
    }// GEN-LAST:event_tipoClaveRSAeActionPerformed

    private void botonLimpiarRSAActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarRSAActionPerformed
        cajaClaveRSAp.setText("");
        cajaClaveRSAq.setText("");
        cajaClaveRSAn.setText("");
        cajaClaveRSAe.setText("");
        cajaClaveRSAd.setText("");
    }// GEN-LAST:event_botonLimpiarRSAActionPerformed

    private void botonClaveRSAActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveRSAActionPerformed
        String result[] = RSACipher.generateKeys();
        cajaClaveRSAp.setText(result[0]);
        cajaClaveRSAq.setText(result[1]);
        cajaClaveRSAn.setText(result[2]);
        cajaClaveRSAe.setText(result[3]);
        cajaClaveRSAd.setText(result[4]);
    }// GEN-LAST:event_botonClaveRSAActionPerformed

    private void panelMetodoRSAFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_panelMetodoRSAFocusGained
        // TODO add your handling code here:
    }// GEN-LAST:event_panelMetodoRSAFocusGained

    private void tipoOptimizacionRSAActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_tipoOptimizacionRSAActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_tipoOptimizacionRSAActionPerformed

    private void cajaClaveRSApKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cajaClaveRSApKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }// GEN-LAST:event_cajaClaveRSApKeyTyped

    private void cajaClaveRSAqKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cajaClaveRSAqKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }// GEN-LAST:event_cajaClaveRSAqKeyTyped

    private void cajaClaveRSAdKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cajaClaveRSAdKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }// GEN-LAST:event_cajaClaveRSAdKeyTyped

    private void cajaClaveRSAeKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cajaClaveRSAeKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }// GEN-LAST:event_cajaClaveRSAeKeyTyped

    private void botonClaveDESActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveDESActionPerformed
        // TODO add your handling code here:
        Random rnd = new Random();
        char[] hexa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'};
        String clave = "";
        clave = "";
        while (clave.length() != 16) {
            char claveChar = hexa[rnd.nextInt(16)];
            clave = clave.concat(String.valueOf(claveChar));
        }
        claveDES.setText(clave);
    }// GEN-LAST:event_botonClaveDESActionPerformed

    private void botonLimpiarDESActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarDESActionPerformed
        // TODO add your handling code here:
        claveDES.setText("");
    }// GEN-LAST:event_botonLimpiarDESActionPerformed

    private void claveDESKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveDESKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        c = Character.toString(c).toUpperCase().charAt(0);

        if (c != 'A' && c != 'B' && c != 'C' && c != 'D' && c != 'E'
                && c != 'F' && !Character.isDigit(c)
                || claveDES.getText().length() == 17) {
            evt.consume();
        } else {
            claveDES.setText(claveDES.getText().concat(String.valueOf(c)));
            evt.consume();
        }
    }// GEN-LAST:event_claveDESKeyTyped

    private void botonClaveTripleDESSActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveTripleDESSActionPerformed
        // TODO add your handling code here:
        Random rnd = new Random();
        String clave = "";
        while (clave.length() != 30) {
            int claveBit = rnd.nextInt(2);
            clave = clave.concat(String.valueOf(claveBit));
        }
        claveTripleDESS.setText(clave);
    }// GEN-LAST:event_botonClaveTripleDESSActionPerformed

    private void botonLimpiarTripleDESSActionPerformed(
            java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarTripleDESSActionPerformed
        // TODO add your handling code here:
        claveTripleDESS.setText("");
    }// GEN-LAST:event_botonLimpiarTripleDESSActionPerformed

    private void claveTripleDESSKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveTripleDESSKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if ((c != '1' && c != '0') || claveTripleDESS.getText().length() == 30) {
            evt.consume();
        }
    }// GEN-LAST:event_claveTripleDESSKeyTyped

    private void botonClaveTDESActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonClaveTDESActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        Random rnd = new Random();
        char[] hexa = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F'};
        String clave = "";
        clave = "";
        while (clave.length() != 48) {
            char claveChar = hexa[rnd.nextInt(16)];
            clave = clave.concat(String.valueOf(claveChar));
        }
        claveTDES.setText(clave);
    }// GEN-LAST:event_botonClaveTDESActionPerformed

    private void botonLimpiarTDESActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonLimpiarTDESActionPerformed
        // TODO add your handling code here:
        claveTDES.setText("");
    }// GEN-LAST:event_botonLimpiarTDESActionPerformed

    private void claveTDESKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_claveTDESKeyTyped
        // TODO add your handling code here:
    }// GEN-LAST:event_claveTDESKeyTyped

    private void cajaClaveRSAnKeyTyped(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_cajaClaveRSAnKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c)) {
            evt.consume();
        }
    }// GEN-LAST:event_cajaClaveRSAnKeyTyped

    private void cajaClaveRSAnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cajaClaveRSAnActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_cajaClaveRSAnActionPerformed

    public int mcd(int a, int b) {
        if (b == 0) {
            return a;
        } else {
            return mcd(b, a % b);
        }

    }

    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    UIManager.setLookAndFeel("org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                new gui().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField L1L2;
    private javax.swing.JButton botonAbrirCifrado;
    private javax.swing.JButton botonAbrirPlano;
    private javax.swing.JButton botonAcercaDe;
    private javax.swing.JButton botonClaveAES;
    private javax.swing.JButton botonClaveAffine;
    private javax.swing.JButton botonClaveCBCMAC;
    private javax.swing.JButton botonClaveD1;
    private javax.swing.JButton botonClaveDES;
    private javax.swing.JButton botonClaveDESS;
    private javax.swing.JButton botonClaveDesplazamiento;
    private javax.swing.JButton botonClaveHill;
    private javax.swing.JButton botonClavePermutacion;
    private javax.swing.JButton botonClaveRSA;
    private javax.swing.JButton botonClaveSPN;
    private javax.swing.JButton botonClaveSustitucion;
    private javax.swing.JButton botonClaveTDES;
    private javax.swing.JButton botonClaveTripleDESS;
    private javax.swing.JButton botonClaveVigenere;
    private javax.swing.JButton botonCriptoanalisis;
    private javax.swing.JButton botonEncriptar;
    private javax.swing.JButton botonGuardarCifrado;
    private javax.swing.JButton botonGuardarPlano;
    private javax.swing.JButton botonLimpiarAES;
    private javax.swing.JButton botonLimpiarAffine;
    private javax.swing.JButton botonLimpiarCBCMAC;
    private javax.swing.JButton botonLimpiarCifrado;
    private javax.swing.JButton botonLimpiarD1;
    private javax.swing.JButton botonLimpiarDES;
    private javax.swing.JButton botonLimpiarDESS;
    private javax.swing.JButton botonLimpiarDesplazamiento;
    private javax.swing.JButton botonLimpiarHill;
    private javax.swing.JButton botonLimpiarPermutacion;
    private javax.swing.JButton botonLimpiarPlano1;
    private javax.swing.JButton botonLimpiarRSA;
    private javax.swing.JButton botonLimpiarSPN;
    private javax.swing.JButton botonLimpiarSustitucion;
    private javax.swing.JButton botonLimpiarSustitucionManual;
    private javax.swing.JButton botonLimpiarTDES;
    private javax.swing.JButton botonLimpiarTripleDESS;
    private javax.swing.JButton botonLimpiarVigenere;
    private javax.swing.JButton botonMuestrasSPN;
    private javax.swing.JButton botonSalir;
    private javax.swing.JButton botonSecuenciaOcultamiento;
    private javax.swing.JButton botonTablaCifrado;
    private javax.swing.JButton buscarParejas;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JTextField cajaClaveRSAd;
    private javax.swing.JTextField cajaClaveRSAe;
    private javax.swing.JTextField cajaClaveRSAn;
    private javax.swing.JTextField cajaClaveRSAp;
    private javax.swing.JTextField cajaClaveRSAq;
    private javax.swing.JTextArea cajaDescripcionAES;
    private javax.swing.JTextArea cajaDescripcionAffine;
    private javax.swing.JTextArea cajaDescripcionCBCMAC;
    private javax.swing.JTextArea cajaDescripcionD1;
    private javax.swing.JTextArea cajaDescripcionDES;
    private javax.swing.JTextArea cajaDescripcionDESS;
    private javax.swing.JTextArea cajaDescripcionDesplazamiento;
    private javax.swing.JTextArea cajaDescripcionHill;
    private javax.swing.JTextArea cajaDescripcionHillClave;
    private javax.swing.JTextArea cajaDescripcionPermutacion;
    private javax.swing.JTextArea cajaDescripcionSPN;
    private javax.swing.JTextArea cajaDescripcionSustitucion;
    private javax.swing.JTextArea cajaDescripcionTDES;
    private javax.swing.JTextArea cajaDescripcionTripleDESS;
    private javax.swing.JTextArea cajaDescripcionVigenere;
    private javax.swing.JTextPane cajaTextoCifrado;
    private javax.swing.JTextPane cajaTextoPlano;
    private javax.swing.JButton calcularL1L3;
    private javax.swing.JTextField claveAES;
    private javax.swing.JTextField claveAffineA;
    private javax.swing.JTextField claveAffineB;
    private javax.swing.JTextField claveCBCMAC;
    private javax.swing.JTextField claveD1;
    private javax.swing.JTextField claveDES;
    private javax.swing.JTextField claveDESS;
    private javax.swing.JTextField claveDesplazamientoCaracter;
    private javax.swing.JTextField claveDesplazamientoNumero;
    private javax.swing.JTextField claveHill1;
    private javax.swing.JTextField claveHill2;
    private javax.swing.JTextField claveHill3;
    private javax.swing.JTextField claveHill4;
    private javax.swing.JTextField claveHill5;
    private javax.swing.JTextField claveHill6;
    private javax.swing.JTextField claveHill7;
    private javax.swing.JTextField claveHill8;
    private javax.swing.JTextField claveHill9;
    private javax.swing.JTextField clavePermutacion;
    private javax.swing.JCheckBox clavePermutacionAlternativa;
    private javax.swing.JTextField claveSPN;
    private javax.swing.JTextField claveSustitucion;
    private javax.swing.JTextField claveSustitucionA;
    private javax.swing.JTextField claveSustitucionB;
    private javax.swing.JTextField claveSustitucionC;
    private javax.swing.JTextField claveSustitucionD;
    private javax.swing.JTextField claveSustitucionE;
    private javax.swing.JTextField claveSustitucionF;
    private javax.swing.JTextField claveSustitucionG;
    private javax.swing.JTextField claveSustitucionH;
    private javax.swing.JTextField claveSustitucionI;
    private javax.swing.JTextField claveSustitucionJ;
    private javax.swing.JTextField claveSustitucionK;
    private javax.swing.JTextField claveSustitucionL;
    private javax.swing.JTextField claveSustitucionM;
    private javax.swing.JTextField claveSustitucionN;
    private javax.swing.JTextField claveSustitucionO;
    private javax.swing.JTextField claveSustitucionP;
    private javax.swing.JTextField claveSustitucionQ;
    private javax.swing.JTextField claveSustitucionR;
    private javax.swing.JTextField claveSustitucionS;
    private javax.swing.JTextField claveSustitucionT;
    private javax.swing.JTextField claveSustitucionU;
    private javax.swing.JTextField claveSustitucionV;
    private javax.swing.JTextField claveSustitucionW;
    private javax.swing.JTextField claveSustitucionX;
    private javax.swing.JTextField claveSustitucionY;
    private javax.swing.JTextField claveSustitucionZ;
    private javax.swing.JTextField claveTDES;
    private javax.swing.JTextField claveTripleDESS;
    private javax.swing.JTextField claveVigenere;
    private javax.swing.JScrollPane contenedorResultadoAffineLetras;
    private javax.swing.JScrollPane contenedorResultadoSusLetras;
    private javax.swing.JScrollPane contenedorResultadosAffineBigramas;
    private javax.swing.JScrollPane contenedorResultadosAffineTrigramas;
    private javax.swing.JScrollPane contenedorResultadosDesplazamiento;
    private javax.swing.JScrollPane contenedorResultadosDesplazamientoBigramas;
    private javax.swing.JScrollPane contenedorResultadosDesplazamientoTrigramas;
    private javax.swing.JScrollPane contenedorResultadosSusBigramas;
    private javax.swing.JScrollPane contenedorResultadosSusTrigramas;
    private javax.swing.JLabel diagramaAES;
    private javax.swing.JLabel diagramaDES;
    private javax.swing.JLabel diagramaDESS;
    private javax.swing.JLabel diagramaRSA;
    private javax.swing.JLabel diagramaTDES;
    private javax.swing.JLabel diagramaTripleDESS;
    private javax.swing.JLabel fondoTitulo;
    private javax.swing.JLabel infoAES;
    private javax.swing.JLabel infoCBCMAC;
    private javax.swing.JLabel infoD1;
    private javax.swing.JLabel infoDES;
    private javax.swing.JLabel infoDESS;
    private javax.swing.JLabel infoHill;
    private javax.swing.JLabel infoPermutacion;
    private javax.swing.JLabel infoSPN;
    private javax.swing.JLabel infoTDES;
    private javax.swing.JLabel infoTripleDESS;
    private javax.swing.JLabel labelClaveActualSust;
    private javax.swing.JLabel labelClaveSustitucion;
    private javax.swing.JLabel labelMuestraDesplazamientoDe;
    private javax.swing.JLabel labelMuestraDesplazamientoa;
    private javax.swing.JLabel labelMuestraSustitucionDe;
    private javax.swing.JLabel labelSusManualA;
    private javax.swing.JLabel labelSusManualB;
    private javax.swing.JLabel labelSusManualC;
    private javax.swing.JLabel labelSusManualD;
    private javax.swing.JLabel labelSusManualE;
    private javax.swing.JLabel labelSusManualF;
    private javax.swing.JLabel labelSusManualG;
    private javax.swing.JLabel labelSusManualH;
    private javax.swing.JLabel labelSusManualI;
    private javax.swing.JLabel labelSusManualJ;
    private javax.swing.JLabel labelSusManualK;
    private javax.swing.JLabel labelSusManualL;
    private javax.swing.JLabel labelSusManualM;
    private javax.swing.JLabel labelSusManualN;
    private javax.swing.JLabel labelSusManualO;
    private javax.swing.JLabel labelSusManualP;
    private javax.swing.JLabel labelSusManualQ;
    private javax.swing.JLabel labelSusManualR;
    private javax.swing.JLabel labelSusManualS;
    private javax.swing.JLabel labelSusManualT;
    private javax.swing.JLabel labelSusManualU;
    private javax.swing.JLabel labelSusManualV;
    private javax.swing.JLabel labelSusManualW;
    private javax.swing.JLabel labelSusManualX;
    private javax.swing.JLabel labelSusManualY;
    private javax.swing.JLabel labelSusManualZ;
    private javax.swing.JLabel muestraCBCMAC;
    private javax.swing.JTextField muestraDesplazamientoA;
    private javax.swing.JTextField muestraDesplazamientoDe;
    private javax.swing.JTextField muestraSustitucionDe;
    private javax.swing.JTextField nParejas;
    private javax.swing.JLabel nTexto;
    private javax.swing.JTextField numeroRondasSPN;
    private javax.swing.JPanel panelAnalisisManualSustitucion;
    private javax.swing.JLayeredPane panelCifradoresBloque;
    private javax.swing.JLayeredPane panelCifradoresClasicos;
    private javax.swing.JLayeredPane panelCifradoresPublicos;
    private javax.swing.JTabbedPane panelClasesCifrados;
    private javax.swing.JPanel panelClaveAES;
    private javax.swing.JPanel panelClaveAffine;
    private javax.swing.JPanel panelClaveCBCMAC;
    private javax.swing.JPanel panelClaveD1;
    private javax.swing.JPanel panelClaveDES;
    private javax.swing.JPanel panelClaveDESS;
    private javax.swing.JPanel panelClaveHill;
    private javax.swing.JPanel panelClaveRSAab;
    private javax.swing.JPanel panelClaveRSAnpq;
    private javax.swing.JPanel panelClaveSPN;
    private javax.swing.JPanel panelClaveTDES;
    private javax.swing.JPanel panelClaveTripleDESS;
    private javax.swing.JLayeredPane panelContenido;
    private javax.swing.JPanel panelCriptoanalisis;
    private javax.swing.JScrollPane panelDescripcionAES;
    private javax.swing.JScrollPane panelDescripcionAffine;
    private javax.swing.JScrollPane panelDescripcionCBCMAC;
    private javax.swing.JScrollPane panelDescripcionD1;
    private javax.swing.JScrollPane panelDescripcionDES;
    private javax.swing.JScrollPane panelDescripcionDESS;
    private javax.swing.JScrollPane panelDescripcionDesplazamiento;
    private javax.swing.JScrollPane panelDescripcionHill;
    private javax.swing.JScrollPane panelDescripcionHillClave;
    private javax.swing.JScrollPane panelDescripcionPermutacion;
    private javax.swing.JScrollPane panelDescripcionSPN;
    private javax.swing.JScrollPane panelDescripcionSustitucion;
    private javax.swing.JScrollPane panelDescripcionTDES;
    private javax.swing.JScrollPane panelDescripcionTripleDESS;
    private javax.swing.JScrollPane panelDescripcionVigenere;
    private javax.swing.JScrollPane panelDetallesAES;
    private javax.swing.JScrollPane panelDetallesCBCMAC;
    private javax.swing.JScrollPane panelDetallesD1;
    private javax.swing.JScrollPane panelDetallesDES;
    private javax.swing.JScrollPane panelDetallesDESS;
    private javax.swing.JScrollPane panelDetallesSPN;
    private javax.swing.JScrollPane panelDetallesTDES;
    private javax.swing.JScrollPane panelDetallesTripleDESS;
    private javax.swing.JLayeredPane panelMetodoAES;
    private javax.swing.JLayeredPane panelMetodoAffine;
    private javax.swing.JLayeredPane panelMetodoCBCMAC;
    private javax.swing.JLayeredPane panelMetodoD1;
    private javax.swing.JLayeredPane panelMetodoDES;
    private javax.swing.JLayeredPane panelMetodoDESS;
    private javax.swing.JLayeredPane panelMetodoDesplazamiento;
    private javax.swing.JLayeredPane panelMetodoHill;
    private javax.swing.JLayeredPane panelMetodoPermutacion;
    private javax.swing.JLayeredPane panelMetodoRSA;
    private javax.swing.JLayeredPane panelMetodoSustitucion;
    private javax.swing.JLayeredPane panelMetodoTDES;
    private javax.swing.JLayeredPane panelMetodoTripleDESS;
    private javax.swing.JLayeredPane panelMetodoVigenere;
    private javax.swing.JTabbedPane panelMetodosBloque;
    private javax.swing.JTabbedPane panelMetodosClasicos;
    private javax.swing.JTabbedPane panelMetodosPublicos;
    private javax.swing.JLayeredPane panelMetodosSPN;
    private javax.swing.JPanel panelMuestraDesplazamiento;
    private javax.swing.JPanel panelMuestraPermutacion;
    private javax.swing.JPanel panelMuestraSustitucion;
    private javax.swing.JPanel panelResultadosAffine;
    private javax.swing.JPanel panelResultadosDesplazamiento;
    private javax.swing.JPanel panelResultadosSustitucion;
    private javax.swing.JPanel panelResultadosVigenere;
    private javax.swing.JPanel panelSecuenciaOcultamiento;
    private javax.swing.JPanel panelTablaCifrado;
    private javax.swing.JScrollPane panelTextoCifrado;
    private javax.swing.JScrollPane panelTextoPlano;
    private javax.swing.JPanel panelTipoClaveDesplazamiento;
    private javax.swing.JPanel panelTipoClaveDesplazamiento1;
    private javax.swing.JLayeredPane panelTitulo;
    private javax.swing.JPanel parejasPanel;
    private javax.swing.JTextField permutacionSPN;
    private javax.swing.JTextField rutaParejas;
    private javax.swing.JTextField secuenciaOcultamiento;
    private javax.swing.JTextField sustitucionSPN;
    private javax.swing.JTable tablaResultadosAffineBigramas;
    private javax.swing.JTable tablaResultadosAffineLetras;
    private javax.swing.JTable tablaResultadosAffineTrigramas;
    private javax.swing.JTable tablaResultadosDesplazamiento;
    private javax.swing.JTable tablaResultadosDesplazamientoBigramas;
    private javax.swing.JTable tablaResultadosDesplazamientoTrigramas;
    private javax.swing.JTable tablaResultadosSusBigramas;
    private javax.swing.JTable tablaResultadosSusLetras;
    private javax.swing.JTable tablaResultadosSusTrigramas;
    private javax.swing.JTextField tablaSimboloSeparador;
    private javax.swing.JLabel textoAffine1;
    private javax.swing.JLabel textoAffine2;
    private javax.swing.JLabel textoAffine3;
    private javax.swing.JLabel textoAffineClave;
    private javax.swing.JLabel textoClaveRSAq;
    private javax.swing.JLabel textoClaveSPN;
    private javax.swing.JLabel textoDescripcion10;
    private javax.swing.JLabel textoDescripcionAES;
    private javax.swing.JScrollPane textoDescripcionAffine;
    private javax.swing.JTextArea textoDescripcionAffineT;
    private javax.swing.JLabel textoDescripcionCBCMAC;
    private javax.swing.JLabel textoDescripcionD1;
    private javax.swing.JLabel textoDescripcionDES;
    private javax.swing.JLabel textoDescripcionDESS;
    private javax.swing.JScrollPane textoDescripcionDesplazamiento;
    private javax.swing.JScrollPane textoDescripcionDesplazamiento1;
    private javax.swing.JTextArea textoDescripcionDesplazamientoT;
    private javax.swing.JTextArea textoDescripcionDesplazamientoT1;
    private javax.swing.JScrollPane textoDescripcionHill;
    private javax.swing.JTextArea textoDescripcionHillT;
    private javax.swing.JScrollPane textoDescripcionPermutacion;
    private javax.swing.JScrollPane textoDescripcionPermutacion1;
    private javax.swing.JTextArea textoDescripcionPermutacionT;
    private javax.swing.JTextArea textoDescripcionPermutacionT1;
    private javax.swing.JLabel textoDescripcionSimboloD1;
    private javax.swing.JScrollPane textoDescripcionSustitucion;
    private javax.swing.JTextArea textoDescripcionSustitucionT;
    private javax.swing.JLabel textoDescripcionTDES;
    private javax.swing.JLabel textoDescripcionTripleDESS;
    private javax.swing.JScrollPane textoDescripcionVigenere;
    private javax.swing.JTextArea textoDescripcionVigenereT;
    private javax.swing.JTextArea textoDetallesAES;
    private javax.swing.JTextArea textoDetallesCBCMAC;
    private javax.swing.JTextArea textoDetallesD1;
    private javax.swing.JTextArea textoDetallesDES;
    private javax.swing.JTextArea textoDetallesDESS;
    private javax.swing.JTextArea textoDetallesSPN1;
    private javax.swing.JTextArea textoDetallesTDES;
    private javax.swing.JTextArea textoDetallesTripleDESS;
    private javax.swing.JLabel textoFuncionamientoAffine;
    private javax.swing.JLabel textoFuncionamientoDesplazamiento;
    private javax.swing.JLabel textoFuncionamientoHill;
    private javax.swing.JLabel textoFuncionamientoPermutacion;
    private javax.swing.JLabel textoFuncionamientoSustitucion;
    private javax.swing.JLabel textoFuncionamientoVigenere;
    private javax.swing.JTextArea textoInfoHill;
    private javax.swing.JLabel textoL1L3;
    private javax.swing.JLabel textoMasProbablesClavesAffine;
    private javax.swing.JLabel textoMasProbablesClavesAffineR;
    private javax.swing.JLabel textoMasProbablesClavesDesplazamiento;
    private javax.swing.JLabel textoMasProbablesClavesDesplazamientoR;
    private javax.swing.JLabel textoMasProbablesClavesSustitucionR;
    private javax.swing.JLabel textoNumeroRondasSPN;
    private javax.swing.JLabel textoParejas;
    private javax.swing.JLabel textoPermutacionSPN;
    private javax.swing.JLabel textoResultadoVigenere;
    private javax.swing.JLabel textoResultadoVigenereClave;
    private javax.swing.JScrollPane textoSustitucionDes;
    private javax.swing.JTextArea textoSustitucionDesT;
    private javax.swing.JLabel textoSustitucionSPN;
    private javax.swing.JLabel textoTipoCifradoAffine;
    private javax.swing.JLabel textoTipoCifradoAffineT;
    private javax.swing.JLabel textoTipoCifradoDesplazamiento;
    private javax.swing.JLabel textoTipoCifradoDesplazamientoT;
    private javax.swing.JLabel textoTipoCifradoHill;
    private javax.swing.JLabel textoTipoCifradoHillT;
    private javax.swing.JLabel textoTipoCifradoPermutacion;
    private javax.swing.JLabel textoTipoCifradoPermutacionT;
    private javax.swing.JLabel textoTipoCifradoSustitucion;
    private javax.swing.JLabel textoTipoCifradoSustitucionT;
    private javax.swing.JLabel textoTipoCifradoVigenere;
    private javax.swing.JLabel textoTipoCifradoVigenereT;
    private javax.swing.JScrollPane textoescripcion6;
    private javax.swing.ButtonGroup tipoClaveDesplazamiento;
    private javax.swing.JRadioButton tipoClaveDesplazamientoCaracter;
    private javax.swing.JRadioButton tipoClaveDesplazamientoNumero;
    private javax.swing.ButtonGroup tipoClaveHill;
    private javax.swing.JRadioButton tipoClaveHill2;
    private javax.swing.JRadioButton tipoClaveHill3;
    private javax.swing.ButtonGroup tipoClaveRSAab;
    private javax.swing.JRadioButton tipoClaveRSAd;
    private javax.swing.JRadioButton tipoClaveRSAe;
    private javax.swing.JRadioButton tipoClaveRSAn;
    private javax.swing.ButtonGroup tipoClaveRSAnpq;
    private javax.swing.JRadioButton tipoClaveRSApq;
    private javax.swing.JCheckBox tipoOptimizacionRSA;
    private javax.swing.JLabel tituloCifrado;
    private javax.swing.JLabel tituloPlano;
    // End of variables declaration//GEN-END:variables
}
