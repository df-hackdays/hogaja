package com.scotiabank.hojaga;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtility {

    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static DatabaseReference getModulesReference() {
        DatabaseReference moduleRef = database.getReference("topics");
        return moduleRef;
    }
}
