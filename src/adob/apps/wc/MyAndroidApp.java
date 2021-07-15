package adob.apps.wc;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

public class MyAndroidApp extends Activity 
{
    /** Called when the activity is first created. */
    /*Declaracion*/
    static final private int BACK_ID = Menu.FIRST;
    static final private int EXIT_ID = Menu.FIRST + 1;
    /*private Button BtnBack;
    private Button BtnExit;*/
    private Button BtnDelete;
    private Button btnEscribirSD;
    private TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        tv = (TextView)findViewById(R.id.tv);
        /*BtnBack = (Button)findViewById(R.id.btnBack);
        BtnExit = (Button)findViewById(R.id.btnExit);*/
        BtnDelete = (Button)findViewById(R.id.btnDelete);
        btnEscribirSD = (Button)findViewById(R.id.btnESD);
    /*
    **̈ called when the user presses the back button
    */
    		/*BtnBack.setOnClickListener(new OnClickListener(){
    			public void onClick(View v){
    				finish();
    			}
    		});*/
    /*
    ** called when the user presses the exit button
    */
    		/*BtnExit.setOnClickListener(new OnClickListener(){
    			public void onClick(View v){
    				finish();
    				onDestroy();
    			}
    		});*/
    		BtnDelete.setOnClickListener(new OnClickListener(){
    			public void onClick(View v){
    				tv.setText("Borrando");
  					/**/
						boolean sdDisponible = false;
						boolean sdAccesoEscritura = false;
						//Comprobamos el estado de la memoria externa (tarjeta SD)
						String estado = Environment.getExternalStorageState();
						if (estado.equals(Environment.MEDIA_MOUNTED)){
							sdDisponible = true;
							sdAccesoEscritura = true;
						}else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
							sdDisponible = true;
							sdAccesoEscritura = false;
						}else {
							sdDisponible = false;
							sdAccesoEscritura = false;
						}
						//Si la memoria externa est� disponible y se puede escribir
						if (sdDisponible && sdAccesoEscritura){
							Thread thd = new Thread(){
								public void run(){
									try {
										File ruta_sd_global = Environment.getExternalStorageDirectory();
										//File ruta_sd_app_musica = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
										File f = new File(ruta_sd_global.getAbsolutePath(), "prueba_sd.txt");
										f.delete();
									}catch (Exception ex){
										Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
										tv.setText("Error");
									}
									try {
										File ruta_sd_global2 = Environment.getExternalStorageDirectory();
										//File ruta_sd_app_musica = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
										File f2 = new File(ruta_sd_global2.getAbsolutePath(), "WhatsApp/Media/WhatsApp Voice Notes");
										File f3 = new File(ruta_sd_global2.getAbsolutePath(), "WhatsApp/Media/WhatsApp Images/Sent");
										File f4 = new File(ruta_sd_global2.getAbsolutePath(), "WhatsApp/Media/WhatsApp Audio/Sent");
										File f5 = new File(ruta_sd_global2.getAbsolutePath(), "WhatsApp/Media/WhatsApp Documents/Sent");
										File f6 = new File(ruta_sd_global2.getAbsolutePath(), "WhatsApp/Media/WhatsApp Video/Sent");
										File f7 = new File(ruta_sd_global2.getAbsolutePath(), "WhatsApp/Media/WhatsApp Animated Gifs/Sent");
										File f8 = new File(ruta_sd_global2.getAbsolutePath(), "prueba_sd");
										DFWC(f2);
										DFWC(f3);
										DFWC(f4);
										DFWC(f5);
										DFWC(f6);
										DFWC(f7);
										DFWC(f8);
										tv.setText(tv.getText()  + ". Por favor presione 3 veces más el boton");
									}catch(Exception e2){
										//tv.setText("Error de Borrado" + e2);
									}
								}
							};thd.start();
						}
    			}
    		});
    /**/   
    
    /**/
			btnEscribirSD.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {	
  				/**//**/
				boolean sdDisponible = false;
				boolean sdAccesoEscritura = false;
				//Comprobamos el estado de la memoria externa (tarjeta SD)
				String estado = Environment.getExternalStorageState();
				if (estado.equals(Environment.MEDIA_MOUNTED)){
					sdDisponible = true;
					sdAccesoEscritura = true;
				} else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
					sdDisponible = true;
					sdAccesoEscritura = false;
				} else {
					sdDisponible = false;
					sdAccesoEscritura = false;
				}
				//Si la memoria externa est� disponible y se puede escribir
				if (sdDisponible && sdAccesoEscritura){
					try {
						File ruta_sd_global = Environment.getExternalStorageDirectory();
						//File ruta_sd_app_musica = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
						File f = new File(ruta_sd_global.getAbsolutePath(), "prueba_sd.txt");
						OutputStreamWriter fout = 
							new OutputStreamWriter(
									new FileOutputStream(f));
						fout.write("Texto de prueba. 111");
						fout.close();
						Log.i("Ficheros", "Fichero SD creado!");
						tv.setText("Fichero SD creado!");
					}
					catch (Exception ex){
						Log.e("Ficheros", "Error al escribir fichero a tarjeta SD");
						tv.setText("Error al escribir fichero a tarjeta SD");
					}
					Thread ht1 = new Thread(){
						public void run(){
							try {
								File ruta_sd_global1 = Environment.getExternalStorageDirectory();
								//File ruta_sd_app_musica = getExternalFilesDir(Environment.DIRECTORY_MUSIC);
								File f1 = new File(ruta_sd_global1.getAbsolutePath(), "prueba_sd");
								f1.mkdirs();
								tv.setText("Directorio creado");
							}catch(Exception e1){
							}
						}
					}; ht1.start();
				}
			}
		});
    /**/  
    }
    /*
    **Called when the activity's is about to starts interacting with user.
    */
    public void onResume(){
 				super.onResume();
    }
    
    /*
    **Called when your activity's options menu needs to be created
    */
    public boolean onCreateOptionsMenu(Menu menu){
    		super.onCreateOptionsMenu(menu);
    		menu.add(0, BACK_ID, 0, R.string.back).setShortcut('0', 'b');
    		menu.add(0, EXIT_ID, 0, R.string.exit).setShortcut('1', 'c');
    		return true;
    }
    /*
    public boolean onPreparateOptionsMenu(Menu menu){
    		super.onPrepareOptionsMenu(menu);
    }*/
    
    public  boolean onOptionsItemSelected(MenuItem item){
    		switch(item.getItemId()){
    			case BACK_ID:{
    				finish();
    				return true;
    			}
    			case EXIT_ID:{
    				finish();
    				onDestroy();
    				return true;
    			}
    		}
    		return super.onOptionsItemSelected(item);
    }
    /**/
    public static void eliminarPorExtension(String path, final String extension){
    	File[] archivos = new File(path).listFiles(new FileFilter() {
        public boolean accept(File archivo) {
            if (archivo.isFile()){
                return archivo.getName().endsWith('.' + extension);
            }
            return false;
        }
    	});
    	for (File archivo : archivos){
        archivo.delete(); 	
      }
		}/*End method*/
		/*Init method*/
		public boolean DFWC(File folder){
				boolean ChildDeleted = true;
			if (!folder.exists()){
				tv.setText("Directorios borrados");
			} else{
				if(folder.delete()){
							tv.setText("Borrado exitosamente");
				}else{
							tv.setText("El directorio posee archivos, borrando");
					if(folder.isDirectory()){
							//obtiene un listado de los archivos contenidoSs en el directorio.
    						String[] Shijos = folder.list();
    						File[] hijos = folder.listFiles();
    						//Elimina los archivos contenidos.
    						for (int i = 0; i < hijos.length; i++){
       							new File(folder, Shijos[i]).delete();
       							File hijo = hijos[i];
       							if (hijo.isDirectory()){
       								ChildDeleted = 	this.DFWC(hijo);	
       							} else if (hijo.exists()) {
       									ChildDeleted = hijo.delete();
       							} else {
       								hijos[i].delete();
       							}
    						}
    						if (folder.delete()){
    							tv.setText("Exitosamente borrado");
    						} else{
    							tv.setText("Error");
    						}
					}else {
						folder.delete();
					}
				}
			}
				return ChildDeleted;	
		}/*End method*/
}
