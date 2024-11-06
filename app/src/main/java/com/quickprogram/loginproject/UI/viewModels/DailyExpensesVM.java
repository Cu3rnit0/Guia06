package com.quickprogram.loginproject.UI.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.quickprogram.loginproject.Models.Gastodia;
import com.quickprogram.loginproject.Repositories.DailyExpensesRepository;

import java.util.ArrayList;
import java.util.List;

public class DailyExpensesVM extends ViewModel {
    private MutableLiveData<List<Gastodia>> expenseLiveData;
    private DailyExpensesRepository repository;
    public DailyExpensesVM(){
        expenseLiveData = new MutableLiveData<>();
        repository = new DailyExpensesRepository();
    }
    public void addDailyExpense(Gastodia mainObject, OnSuccessListener<DocumentReference> onSuccess, OnFailureListener
            onFailure) {
        repository.addDailyExpense(mainObject, onSuccess, onFailure);
    }
    public void finalizeBudget(String budgetId, OnSuccessListener<Void> onSuccess, OnFailureListener onFailure) {
        repository.endBudget(budgetId, onSuccess, onFailure);
    }
    public LiveData<List<Gastodia>> getDailyExpensesLiveData() {
        return expenseLiveData;
    }
    public void listenForExpensesChanges(String presupuestoId) {
        repository.listenForExpensesChanges(presupuestoId, (querySnapshot, e) -> {
            if (e != null) {
                return;
            }
            List<Gastodia> gastosDias = new ArrayList<>();
            for (QueryDocumentSnapshot document : querySnapshot) {
                Gastodia mainObject = document.toObject(Gastodia.class);
                gastosDias.add(mainObject);
            }
            expenseLiveData.postValue(gastosDias);
        });
    }
}
