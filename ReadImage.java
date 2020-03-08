
package readimage;
import java.math.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.DataBufferInt;
import static java.awt.image.ImageObserver.HEIGHT;
import static java.awt.image.ImageObserver.WIDTH;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static javax.swing.Spring.height;

public class ReadImage {

    
    public static void main(String[] args) throws IOException {
        File file = new File("E:\\Pics\\img22gd2.jpg");
        BufferedImage img = ImageIO.read(file);
        int width = img.getWidth();
        int height = img.getHeight();
        int[][] imgArr = new int[width][height];
        Raster raster = img.getData();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                imgArr[i][j] = raster.getSample(i, j, 0);
                //System.out.print(imgArr[i][j]+" ");
            }
            //System.out.println();
        }
        
        
        int[][] imgArr1 = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imgArr1[i][j] = imgArr[j][i];
                System.out.print(imgArr1[i][j]+"("+i+","+j+")"+" ");
            }
            System.out.println();
        }
        
        Pixel p=new Pixel(67,45);
        int[][] ConSet = new int[height][width];
        ConSet=ConnectedSet(p,imgArr1,width,height,2);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                
                System.out.print(ConSet[i][j]+"("+i+","+j+")"+" ");
            }
            System.out.println();
        }
        
        
        
        //WriteImage
//        byte[] outputImagePixelData = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
//
//        int width1 = img.getWidth();
//        int height1 = img.getHeight();
//
//        for (int y = 0; y < width1; y++) {
//            for (int x = 0; x < height1; x++) {
//                outputImagePixelData[y * width + x] = (byte) imgArr[x][y];
//            }
//        }
//        ImageIO.write(img, "jpg", new File("E:\\Pics\\output.tif"));

//    BufferedImage imag = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
//    int[] outputImagePixelData = ((DataBufferInt) imag.getRaster().getDataBuffer()).getData() ;
//
//    final int width1 = img.getWidth() ;
//    final int height1 = img.getHeight() ;
//
//    for (int y=0, pos=0 ; y < height1 ; y++)
//        for (int x=0 ; x < width1 ; x++, pos++)
//            outputImagePixelData[pos] = ConSet[y][x] ;
//    
//    ImageIO.write(imag, "jpg", new File("E:\\Pics\\output.tif"));





    BufferedImage imag = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
    byte[] outputImagePixelData = ((DataBufferByte) imag.getRaster().getDataBuffer()).getData() ;

    final int width1 = img.getWidth() ;
    final int height1 = img.getHeight() ;

    for (int y=0, pos=0 ; y < height1 ; y++)
        for (int x=0 ; x < width1 ; x++, pos++)
            outputImagePixelData[pos] = (byte) ConSet[y][x] ;
    
    ImageIO.write(imag, "jpg", new File("E:\\Pics\\output1.tif"));
    
    
    
    
    
    

//    byte[] outputImagePixelData = ((DataBufferByte) img.getRaster().getDataBuffer()).getData() ;
//
//    final int width1 = img.getWidth() ;
//    final int height1 = img.getHeight() ;
//
//    for (int y=0, pos=0 ; y < height1 ; y++)
//        for (int x=0 ; x < width1 ; x++, pos++)
//            outputImagePixelData[pos] = (byte) ConSet[y][x] ;
//    
//    ImageIO.write(img, "jpg", new File("E:\\Pics\\output.tif"));




    }
    
    
    public static int[][] ConnectedSet(Pixel p,int arr[][],int width,int height,int T)
    {
        int[][] imgArr = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                imgArr[i][j] = 0;
                //System.out.print(imgArr[i][j]+" ");
            }
            //System.out.println();
        }
        Stack<Pixel> s = new Stack<Pixel>(); 
        s.push(p);
        while(!s.empty())
        {
            Pixel temp=s.pop();
            Pixel temp1=new Pixel(0,0);
            imgArr[temp.x][temp.y]=255;
            if (temp.y!=0 && Math.abs(arr[temp.x][temp.y]-arr[temp.x][temp.y-1])<=T && imgArr[temp.x][temp.y-1]==0)
            {
                temp1.x=temp.x;
                temp1.y=temp.y-1;
                s.push(temp1);
            }
            if (temp.y!=width-1 && Math.abs(arr[temp.x][temp.y]-arr[temp.x][temp.y+1])<=T && imgArr[temp.x][temp.y+1]==0)
            {
                temp1.x=temp.x;
                temp1.y=temp.y+1;
                s.push(temp1);
            }
            if (temp.x!=0 && Math.abs(arr[temp.x][temp.y]-arr[temp.x-1][temp.y])<=T  && imgArr[temp.x-1][temp.y]==0)
            {
                temp1.x=temp.x-1;
                temp1.y=temp.y;
                s.push(temp1);
            }
            if (temp.x!=height-1 && Math.abs(arr[temp.x][temp.y]-arr[temp.x+1][temp.y])<=T && imgArr[temp.x+1][temp.y]==0)
            {
                temp1.x=temp.x+1;
                temp1.y=temp.y;
                s.push(temp1);
            }
        }
        return imgArr;
    }
    

    

    
}
