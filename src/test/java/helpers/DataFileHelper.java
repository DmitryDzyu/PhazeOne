package helpers;

import java.io.*;

public class DataFileHelper {

    /**Запись в файл*/
    public static void write(String fileName, String text) {
        //Определяем файл
        File file = new File(fileName);

        try {
            //проверяем, что если файл не существует то создаем его
            if(!file.exists()){
                file.createNewFile();
            }

            //PrintWriter обеспечит возможности записи в файл
            FileWriter out = new FileWriter(file.getAbsoluteFile());

            try {
                //Записываем текст у файл
                out.write(text);
            } finally {
                //После чего мы должны закрыть файл
                //�?наче файл не запишется
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
    /**Перезапись файлика*/
    public static void rewriteFile(String fileName,String newText)throws IOException{
        StringBuilder sb = new StringBuilder();
        String oldFile = read(fileName) ;
        sb.append(oldFile);
        sb.append(newText);
        write(fileName,sb.toString());
    }

    /**Чтение из файла*/
    public static String read(String fileName) throws IOException {
        //Этот спец. объект для построения строки
        StringBuilder sb = new StringBuilder();
        File file = new File(fileName);
        if(!file.exists()){
            file.createNewFile();
        }
        try {
            //Объект для чтения файла в буфер
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            } finally {
                //Также не забываем закрыть файл
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
