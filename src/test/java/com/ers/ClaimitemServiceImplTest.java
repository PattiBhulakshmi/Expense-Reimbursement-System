package com.ers;

import com.ers.dao.IClaimItemDao;
import com.ers.model.ClaimItem;
import com.ers.service.ClaimItemServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClaimitemServiceImplTest {

    private IClaimItemDao claimItemDao;
    private ClaimItemServiceImpl claimItemService;

    @BeforeEach
    void setUp() {
        claimItemDao = mock(IClaimItemDao.class);
        claimItemService = new ClaimItemServiceImpl(claimItemDao);
    }

    @Test
    void testAddClaimItem() {

        ClaimItem claimItem = new ClaimItem();

        when(claimItemDao.addClaimItem(claimItem)).thenReturn(true);

        boolean result = claimItemService.addClaimItem(claimItem);
        assertTrue(result);

        verify(claimItemDao).addClaimItem(claimItem);
    }

    @Test
    void testAddClaimItemFailure() {

        ClaimItem claimItem = new ClaimItem();

        when(claimItemDao.addClaimItem(claimItem)).thenReturn(false);

        boolean result = claimItemService.addClaimItem(claimItem);

        assertFalse(result);

        verify(claimItemDao).addClaimItem(claimItem);
    }

    @Test
    void testGetClaimItemsByClaimId() {

        int claimId = 1;

        ClaimItem item1 = new ClaimItem();
        ClaimItem item2 = new ClaimItem();

        List<ClaimItem> claimItems = new ArrayList<>();

        claimItems.add(item1);
        claimItems.add(item2);

        when(claimItemDao.getClaimItemsByClaimId(claimId)).thenReturn(claimItems);

        List<ClaimItem> result = claimItemService.getClaimItemsByClaimId(claimId);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(claimItems, result);

        verify(claimItemDao).getClaimItemsByClaimId(claimId);
    }
}
