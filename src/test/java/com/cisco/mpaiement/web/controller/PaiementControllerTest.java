package com.cisco.mpaiement.web.controller;

import com.cisco.mpaiement.dao.PaiementDao;
import com.cisco.mpaiement.model.Paiement;
import com.cisco.mpaiement.web.exceptions.PaiementExistantException;
import com.cisco.mpaiement.web.exceptions.PaiementImpossibleException;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PaiementControllerTest {

    private static Paiement paiement;

    @Mock
    PaiementDao paiementDao;

    @InjectMocks
    PaiementController paiementController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paiement = new Paiement(1,1,150000,5550L);
    }

    @Test
    void payerUneCommande() {
        Mockito.when(paiementDao.save(paiement)).thenReturn(paiement);
        assertThat(paiementController.payerUneCommande(paiement).getStatusCode(), is(HttpStatus.CREATED));
    }

    @Test
    void paiementCommandeDejaEffectue() {
        Mockito.when(paiementDao.findByidCommande(paiement.getIdCommande())).thenReturn(paiement);
        Assertions.assertThrows(PaiementExistantException.class, () -> {
            paiementController.payerUneCommande(paiement);
        });
    }

    @Test
    void payerUneCommandeImpossible() {
        Mockito.when(paiementDao.save(paiement)).thenReturn(null);
        Assertions.assertThrows(PaiementImpossibleException.class, () -> {
            paiementController.payerUneCommande(paiement);
        });
    }
}