package com.company.Utils;

import com.company.Log;

import java.io.*;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 1.0.0 11/20/13
 */
public class UtilSerializableFile<T>{
    private File file;

    public UtilSerializableFile(String filename) {
        this.file = new File(filename);
    }

    public void save(T object) {
        try(FileOutputStream f_out = new FileOutputStream(file)) {
            ObjectOutputStream obj_out = new ObjectOutputStream(f_out);
            obj_out.writeObject(object);
            f_out.close();
        } catch (IOException e) {
            Log.save(e);
        }
    }

    public boolean exists() {
        return file.exists();
    }

    public T load() {
        T object = null;
        try(FileInputStream f_in = new FileInputStream(file)) {
            ObjectInputStream obj_in = new ObjectInputStream(f_in);
            object = (T) obj_in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            Log.save(e);
        }
        return object;
    }
}
