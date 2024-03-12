public class FileDetails {
    String name, path;
    public FileDetails(String name, String path){
        this.name = name;
        this.path = path;
    }

    public String getFile(){
        return name;
    }

    public String getPath(){
        return path;
    }
}
