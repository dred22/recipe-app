package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

public class IndexControllerTest {

    @Mock
    private RecipeService recipeService;
    @Mock
    private Model model;
    @InjectMocks
    private IndexController indexController;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getIndexPage() {


        Assert.assertEquals("index", indexController.getIndexPage(model));
        Mockito.verify(recipeService).getRecipes();
        Mockito.verify(model).addAttribute(Mockito.eq("recipes"), Mockito.anySet());

    }
}