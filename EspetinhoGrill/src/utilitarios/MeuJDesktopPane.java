package utilitarios;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

public class MeuJDesktopPane extends JDesktopPane {

    Image imagem = new ImageIcon(getClass().getResource("/imagem/fundo3.jpg")).getImage();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dimension = this.getSize();
        int x = (int) (dimension.getWidth() - imagem.getWidth(this)) / 2;
        int y = (int) (dimension.getHeight() - imagem.getHeight(this)) / 2;
        //g.drawImage(imagem, x, y, imagem.getWidth(this), imagem.getHeight(this), this);
        g.drawImage(imagem, 0, 0, getSize().width, getSize().height, this);
    }

    public void remove(MouseAdapter aThis) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}