package com.marc.arseneault.tp4.model.repository;

import android.content.Context;
import com.google.gson.Gson;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.marc.arseneault.tp4.model.Transaction;

/**
 * Created by 1333297 on 2015-04-29.
 */
public class TransactionRepository implements CRUD<Transaction> {

        Gson gson = new Gson();

        Class<Transaction> classe = Transaction.class;

        Context context;

        public TransactionRepository(Context c){
            this.context = c;
            this.createStorage();
        }

        public List<Transaction> getAll() {
            synchronized (classe) {
                List<Transaction> res = new ArrayList<Transaction>();
                File base = context.getFilesDir();
                for (File f : base.listFiles()){
                    try{
                        //System.out.println("File is "+f.getName());
                        String content = FileUtils.readFileToString(f);
                        Transaction a = gson.fromJson(content, classe);
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

        public long save(Transaction a) {
            synchronized (classe) {
                // set the id
                if (a.getId() == null) a.setId(this.nextAvailableId());
                //
                String serialise = gson.toJson(a);
                File base = context.getFilesDir();
                try {
                    FileUtils.writeStringToFile(new File(base, a.getId()+".Transaction"), serialise);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return a.getId();

            }
        }

        @Override
        public void saveMany(Iterable<Transaction> list) {
            for (Transaction p : list ){
                this.save(p);
            }
        }

        @Override
        public void saveMany(Transaction... list) {
            for (Transaction p : list ){
                this.save(p);
            }
        }

        @Override
        public Transaction getById(Long id) {
            synchronized (classe) {
                String content;
                try {
                    File base = context.getFilesDir();
                    File f = new File(base,id+".Transaction");
                    if (!f.exists()) return null;
                    content = FileUtils.readFileToString(new File(base,id+".Transaction"));
                    Transaction a = gson.fromJson(content, classe);
                    return a;
                } catch (IOException e) {
                    return null;
                }
            }
        }

        public void deleteOne(Transaction a) {
            synchronized (classe) {
                File base = context.getFilesDir();
                File f = new File(base, a.getId()+".Transaction");
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
                for (Transaction a : getAll()){
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
