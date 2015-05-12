package com.marc.arseneault.tp4;

import android.test.AndroidTestCase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 1333297 on 2015-04-29.
 */
public class ProductRepositoryTest  extends AndroidTestCase {





        CRUD<Product> repository;

        @Override
        protected void setUp() throws Exception {
            super.setUp();
            repository = new ProductRepository(getContext());
            repository.deleteAll();

        }

        @Override
        protected void tearDown() throws Exception {
            super.tearDown();
            repository.deleteAll();
            repository = null;
        }

        public void testSaveAndGetAll(){
            Product p = new Product("produit", "1333297", 10.0);
            repository.save(p);
            assertEquals(repository.getAll().size(), 1);
        }

        public void testSaveManyAndGetAll(){
            List<Product> prods = new ArrayList<Product>();
            int size = 22;
            for (int i = 0 ; i < size ; i++){
                Product p = new Product("produit"+i, "1333297"+i, 10.0+i);
                prods.add(p);
            }
            repository.saveMany(prods);
            assertEquals(size, repository.getAll().size());
        }

        public void testGetById(){
            Product p = new Product("produit", "1333297", 10.0);
            long tested = repository.save(p);
            Product recov = repository.getById(tested);
            assertEquals(recov.getPrice(), 10.0);
        }

        public void testDeleteOne(){
            Product p = new Product("produit", "1333297", 10.0);
            repository.save(p);
            assertEquals(1, repository.getAll().size());
            repository.deleteOne(p);
            assertEquals(0, repository.getAll().size());
        }

        public void testDeleteOneById(){
            Product p = new Product("produit", "1333297", 10.0);
            repository.save(p);
            assertEquals(1, repository.getAll().size());
            repository.deleteOne(p.getId());
            Log.i("TestsCRUD", repository.getAll().toString());
            assertEquals(0, repository.getAll().size());
        }

        public void testDeleteAll(){
            List<Product> prods = new ArrayList<Product>();
            int size = 22;
            for (int i = 0 ; i < size ; i++){
                Product p = new Product("produit"+i, "1333297"+i, 10.0+i);
                prods.add(p);
            }
            repository.saveMany(prods);
            assertEquals(size, repository.getAll().size());
            repository.deleteAll();
            assertEquals(0, repository.getAll().size());
        }

        public void testSaveOnePerformance(){
            long a = System.currentTimeMillis();
            for (int i = 0 ; i < 300 ; i++){
                Product p = new Product("produit"+i, "1333297"+i, 10.0+i);
                repository.save(p);
            }
            long b = System.currentTimeMillis();
            Log.i("TestLoad", repository.getClass().getSimpleName()+"  : temps est " + (b - a) + " ms");
        }

        public void testScanUPCNull() {
            Product p = new Product("produit", "1333297", 10.0);
            repository.save(p);
            try {
                repository.getByUPC("");
                //simulation idiote d'un assert.fail
                assertEquals(false, true);
            } catch (NullPointerException e) {
                //Success!
            }
        }

        public void testScanProduitNonexistant() {
            Product p = new Product("produit", "1333297", 10.0);
            repository.save(p);
            assertEquals(repository.getByUPC("42"), null);
        }

        public void testCreateProducts() {
            TP4Activity.createProducts(repository);
            assertEquals(repository.getAll().size(), 20);
        }

}
