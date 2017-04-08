import java.io.*;
import java.util.*;
class myfsearch{
	public static void main(String[] args) throws Exception {
		System.out.println("if u have path press 1 else 0");
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		System.out.println("enter line for search");
		in.nextLine();
		String line=in.nextLine();
		thread.line=line;
		if(n==0){
			File dri[]=File.listRoots();
			for (File mydri : dri) {
				File folder[]=mydri.listFiles();
					if(folder!=null){
						for (File fff : folder) {
							
							if(fff.isDirectory()){
								new thread(fff.getParent()+fff.getName()).start();
							}else if (fff.getName().endsWith(".txt") || fff.getName().endsWith(".pdf") || fff.getName().endsWith(".docx")) {
								new thread().file(fff.getParent()+fff.getName());	
							}
						}
						
					}

			}
		}
		else{
		System.out.println("enter path for search");
		String path=in.nextLine();
		File f=new File(path);
		File ff[]=f.listFiles();
		for (File ffs : ff) {
			
			if(ffs.isDirectory()){
				new thread(path+"\\"+ffs.getName()).start();

			}else if(ffs.getName().endsWith(".txt") || ffs.getName().endsWith(".pdf") || ffs.getName().endsWith(".docx")){
				try{
				new thread().file(path+"\\"+ffs.getName());
				}catch(Exception e){}
			}
		}
		}
	}
}

class thread extends Thread{
	String path="";
	public static String line="";
	thread(){}
	thread(String ss){path=ss;}


	public void run(){
		try{
			thread.dir(path);
		}catch(Exception e){}
		
	}

	public static void dir(String mypath) throws Exception{
		File ffs=new File(mypath);
		File f[]=ffs.listFiles();
		for (File ff : f) {
			if(ff.isDirectory()){
				new thread(mypath+"\\"+ff.getName()).start();
			}else if(ff.getName().endsWith(".txt") || ff.getName().endsWith(".pdf") || ff.getName().endsWith(".docx")){
				new thread().file(mypath+"\\"+ff.getName());
			}
		}

	}

	public void file(String path)throws Exception{
		String str="";

		BufferedReader br=new BufferedReader(new FileReader(path));
		while ((str=br.readLine())!=null) {
			
			if(str.equals(thread.line)){
				Process p=Runtime.getRuntime().exec("C:/Windows/Notepad.exe "+path);
			}
		}
	}
}