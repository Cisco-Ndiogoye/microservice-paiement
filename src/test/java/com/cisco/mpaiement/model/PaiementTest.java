package com.cisco.mpaiement.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

class PaiementTest {

    private static Paiement paiement;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        paiement = new Paiement(1,1,150000,5550L);
    }

    @Test
    void getId() {
        assertThat(paiement.getIdCommande(), is(1));
    }

    @Test
    void setId() {
        paiement.setId(2);
        assertThat(paiement.getId(), not(1));
    }

    @Test
    void getIdCommande() {
        assertThat(paiement.getIdCommande(), is(1));
    }

    @Test
    void setIdCommande() {
        paiement.setIdCommande(2);
        assertThat(paiement.getIdCommande(), is(2));
    }

    @Test
    void getMontant() {
        assertThat(paiement.getMontant(), is(150000));
    }

    @Test
    void setMontant() {
        paiement.setMontant(250000);
        assertThat(paiement.getMontant(), is(250000));
    }

    @Test
    void getNumeroCarte() {
        assertThat(paiement.getNumeroCarte(), is(5550L));
    }

    @Test
    void setNumeroCarte() {
        paiement.setNumeroCarte(6550L);
        assertThat(paiement.getNumeroCarte(), not(5550L));
    }

    @Test
    void testToString() {
        String value = "Paiement{" +
                "id=" + paiement.getId();
        Assertions.assertTrue(paiement.toString().contains(value));
    }
}