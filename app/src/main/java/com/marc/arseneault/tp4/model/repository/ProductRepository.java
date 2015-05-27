package com.marc.arseneault.tp4.model.repository;

import android.content.Context;

import java.io.File;
import java.io.IOException;
import java.util.*;
import org.apache.commons.io.FileUtils;
import com.google.gson.Gson;

import com.marc.arseneault.tp4.model.Product;

/**
 * Created by 1333297 on 2015-04-29.
 */
public class ProductRepository  implements CRUD<Product> {


        Gson gson = new Gson();

        Class<Product> classe = Product.class;

        Context context;

        public ProductRepository(Context c){
            this.context = c;
            this.createStorage();
        }

        public List<Product> getAll() {
            synchronized (classe) {
                List<Product> res = new ArrayList<Product>();
                File base = context.getFilesDir();
                for (File f : base.listFiles()){
                    try{
                        //System.out.println("File is "+f.getName());
                        String content = FileUtils.readFileToString(f);
                        Product a = gson.fromJson(content, classe);
                        res.add(a);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
                return res;
            }
        }

        @Override
        public void deleteOne(Long o) {
            this.deleteOne(this.getById(o));
        }

        public long save(Product a) {
            synchronized (classe) {
                // set the id
                if (a.getId() == null) a.setId(this.nextAvailableId());
                //
                String serialise = gson.toJson(a);
                File base = context.getFilesDir();
                try {
                    FileUtils.writeStringToFile(new File(base, a.getId()+".Product"), serialise);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return a.getId();

            }
        }

        @Override
        public void saveMany(Iterable<Product> list) {
            for (Product p : list ){
                this.save(p);
            }
        }

        @Override
        public void saveMany(Product... list) {
            for (Product p : list ){
                this.save(p);
            }
        }

        @Override
        public Product getById(Long id) {
            synchronized (classe) {
                String content;
                try {
                    File base = context.getFilesDir();
                    File f = new File(base,id+".Product");
                    if (!f.exists()) return null;
                    content = FileUtils.readFileToString(new File(base,id+".Product"));
                    Product a = gson.fromJson(content, classe);
                    return a;
                } catch (IOException e) {
                    return null;
                }
            }
        }

        public Product getByUPC(String pUPC) {
            synchronized (classe) {
                if (pUPC.isEmpty())
                    throw new NullPointerException();
                for (Product product: getAll())
                {
                    String UPC = product.getUPC();
                    if (UPC.equals(pUPC))
                        return product;
                }
                return null;
            }
        }

        public void deleteOne(Product a) {
            synchronized (classe) {
                File base = context.getFilesDir();
                File f = new File(base, a.getId()+".Product");
                f.delete();
            }
        }

        public void deleteAll() {
            deleteStorage();
            createStorage();
        }

        // autre methodes hors acces aux donnees pour la gestion.

        private long nextAvailableId(){
            synchronized (classe) {
                long max = 0;
                for (Product a : getAll()){
                    if (a.getId() > max) max = a.getId();
                }
                return max+1;
            }
        }

        public void deleteStorage(){
            File base = context.getFilesDir();
            deleteFolder(base);
        }

        public void createStorage(){
            File base = context.getFilesDir();
            base.mkdirs();
        }

        private static void deleteFolder(File folder) {
            try{File[] files = folder.listFiles();
                if(files!=null) {
                    for(File f: files) {
                        if(f.isDirectory())
                            deleteFolder(f);
                        else
                            f.delete();
                    }
                }
                folder.delete();
            }catch(Exception e){}
        }

}