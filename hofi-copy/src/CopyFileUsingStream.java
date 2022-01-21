import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CopyFileUsingStream {

    public static String destinationTemp;
    public static String sourceFolderFromFile;
    public static String sourceFolderFromFile2;

    public static void main(String[] args) throws IOException {

        String sorureTemp = fileRead().get(0);
        destinationTemp = fileRead().get(1);
        System.out.println("I: " + sorureTemp + "II.: " + destinationTemp);
////        teszt
         sourceFolderFromFile = "C:" + sorureTemp;
         sourceFolderFromFile2 = "C:" + sorureTemp;

//         éles
//       sourceFolderFromFile = "D:" + sorureTemp;
//       sourceFolderFromFile2 = "E:" + sorureTemp;

//        kell mindenképp
        directoryCheck(Path.of(sourceFolderFromFile), Path.of(sourceFolderFromFile2));
        deleteSourceFoldersFiles();

    }

    private static void deleteSourceFoldersFiles() throws IOException {
        Files.walk(Paths.get(sourceFolderFromFile))
                        .filter(Files::isRegularFile)
                        .map(Path::toFile)
                        .forEach(File::delete);
        System.out.println("a törlés müködik");
    }

    public static ArrayList<String> fileRead() throws IOException {
        ArrayList<String> result = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader("\\\\192.168.1.69\\htdocs\\riport\\options.txt"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                for (String str : values) {
                    result.addAll(List.of(values));
                    System.out.println(str);
                }
            }
            br.close();
        return result;
    }

    public static void directoryCheck(Path sourceOne, Path sourceTwo) throws IOException {
        if (Files.exists(sourceOne)){
            copyDirectory(String.valueOf(sourceOne), destinationTemp);
            System.out.println("az elso mukodik");
        }else if(Files.exists(sourceTwo)){
            copyDirectory(String.valueOf(sourceTwo), destinationTemp);
            System.out.println("a második mukodik");
        }
    }

    public static void copyDirectory(String sourceDirectoryLocation, String destinationDirectoryLocation)
            throws IOException {
        Files.walk(Paths.get(sourceDirectoryLocation))
                .forEach(source -> {
                    Path destination = Paths.get(destinationDirectoryLocation, source.toString()
                            .substring(sourceDirectoryLocation.length()));
                    try {
                        Files.copy(source, destination);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }
}


