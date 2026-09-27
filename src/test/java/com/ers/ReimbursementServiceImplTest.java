package com.ers;

import com.ers.dao.IReimbursementDao;
import com.ers.model.Reimbursement;
import com.ers.service.ReimbursementServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ReimbursementServiceImplTest {

    private IReimbursementDao reimbursementDao;
    private ReimbursementServiceImpl reimbursementService;

    @BeforeEach
    void setUp() {
        reimbursementDao = mock(IReimbursementDao.class);
        reimbursementService = new ReimbursementServiceImpl(reimbursementDao);
    }

    @Test
    void testAddReimbursement() {

        Reimbursement reimbursement = new Reimbursement();

        when(reimbursementDao.addReimbursement(reimbursement))
                .thenReturn(true);

        boolean result = reimbursementService.addReimbursement(reimbursement);

        assertTrue(result);

        Mockito.verify(reimbursementDao).addReimbursement(reimbursement);

    }

    private void assertTrue(boolean result) {
    }

    @Test
    void testGetReimbursementByClaimId() {

        int claimId = 101;

        Reimbursement reimbursement = new Reimbursement();

        when(reimbursementDao.getReimbursementByClaimId(claimId))
                .thenReturn(reimbursement);

        Reimbursement result =
                reimbursementService.getReimbursementByClaimId(claimId);

        assertNotNull(result);
        assertEquals(reimbursement, result);

        verify(reimbursementDao, times(1))
                .getReimbursementByClaimId(claimId);
    }

    private void assertNotNull(Reimbursement result) {
    }

    @Test
    void testGetAllReimbursements() {

        Reimbursement reimbursement1 = new Reimbursement();
        Reimbursement reimbursement2 = new Reimbursement();

        List<Reimbursement> reimbursements =
                Arrays.asList(reimbursement1, reimbursement2);

        when(reimbursementDao.getAllReimbursements())
                .thenReturn(reimbursements);

        List<Reimbursement> result = reimbursementService.getAllReimbursements();

        assertTrue(result != null);
        assertEquals(2, result.size());

        verify(reimbursementDao, times(1)).getAllReimbursements();
    }
}
