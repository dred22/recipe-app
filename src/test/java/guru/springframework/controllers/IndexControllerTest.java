package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipes.add(recipe);
        recipe = new Recipe();
        recipe.setId(2L);
        recipes.add(recipe);
        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    public void getIndexPage() {
        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        Assert.assertEquals("index", indexController.getIndexPage(model));
        Mockito.verify(recipeService).getRecipes();
        Mockito.verify(model).addAttribute(Mockito.eq("recipes"), argumentCaptor.capture());
        Assert.assertEquals(2, argumentCaptor.getValue().size());

    }
}