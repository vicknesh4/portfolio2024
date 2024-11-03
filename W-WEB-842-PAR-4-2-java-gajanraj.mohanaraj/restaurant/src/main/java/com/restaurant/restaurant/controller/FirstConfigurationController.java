package com.restaurant.restaurant.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.restaurant.restaurant.model.Card;     
import com.restaurant.restaurant.model.FirstConfiguration;
import com.restaurant.restaurant.model.Menu;
import com.restaurant.restaurant.model.Plats;
import com.restaurant.restaurant.service.CardService;
import com.restaurant.restaurant.service.FirstConfigurationService;
import com.restaurant.restaurant.service.MenuService;
import com.restaurant.restaurant.service.PlatsService;
import com.restaurant.restaurant.util.ImageUtils;


@Controller
@RequestMapping("/config")
public class FirstConfigurationController {

    private final FirstConfigurationService firstConfigurationService;
    private final CardService cardService;
    private final MenuService menuService;
    private final PlatsService platsService;

    @Autowired
    public FirstConfigurationController(FirstConfigurationService firstConfigurationService, CardService cardService, MenuService menuService, PlatsService platsService) {
        this.firstConfigurationService = firstConfigurationService;
        this.cardService = cardService;
        this.menuService = menuService;
        this.platsService = platsService;
    }


    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @GetMapping("/firstConfig")
    public String getFirstConfigTemplate() {
        return "first_configuration";
    }

 @PostMapping("/postNewConfig")
public String postNewConfig(
        @RequestParam String name,
        @RequestParam String url,
        @RequestParam String colorTitle,
        @RequestParam String colorButton,
        @RequestParam String menu,
        @RequestParam String password,
        @RequestParam("photo") MultipartFile photoFile, 
        Model model) {

    String token = UUID.randomUUID().toString();
    try {
   
        byte[] photoBytes = photoFile.getBytes();

        FirstConfiguration firstConfiguration = new FirstConfiguration();
        firstConfiguration.setName(name);
        firstConfiguration.setUrl(url);
        firstConfiguration.setColorTitle(colorTitle);
        firstConfiguration.setColorButton(colorButton);
        firstConfiguration.setMenu(menu);
        firstConfiguration.setPassword(password);
        firstConfiguration.setToken(token);
        firstConfiguration.setPhoto(photoBytes); 

        firstConfigurationService.createFirstConfiguration(firstConfiguration);

        model.addAttribute("name", name);
        model.addAttribute("url", url);
        model.addAttribute("colorTitle", colorTitle);
        model.addAttribute("colorButton", colorButton);
        model.addAttribute("menu", menu);
        return "redirect:/config/newPage/" + token;
    } catch (Exception e) {
        e.printStackTrace();
        return "error";
    }
}

    @GetMapping("/newPage/{token}")
    public String showNewPage(@PathVariable String token, Model model) {
        Optional<FirstConfiguration> firstConfigOptional = firstConfigurationService.findByToken(token);
        if (firstConfigOptional.isPresent()) {
            FirstConfiguration firstConfig = firstConfigOptional.get();
            model.addAttribute("name", firstConfig.getName());
            model.addAttribute("url", firstConfig.getUrl());
            model.addAttribute("colorTitle", firstConfig.getColorTitle());
            model.addAttribute("colorButton", firstConfig.getColorButton());
            model.addAttribute("menu", firstConfig.getMenu());
            model.addAttribute("token", firstConfig.getToken());
            if (firstConfig.getPhoto() != null) {
                System.out.println("ici correct");
                String base64Image = ImageUtils.encodeImageToBase64(firstConfig.getPhoto());
                model.addAttribute("photoBase64", "data:image/jpeg;base64," + base64Image);
                String base64ImageDataUrl = "data:image/jpeg;base64," + base64Image;
        
                
            }
            if (firstConfig.getCard() == 1) {
                System.out.println("je suis la hehehehe");
                model.addAttribute("card", firstConfig.getCard());
                long idTemplate = firstConfig.getId();
                List<Card> cardList = cardService.findByIdSite(idTemplate);
            
                if (!cardList.isEmpty()) {
                    for (Card card : cardList) {
                        System.out.println("injaizdekjcz");
                        model.addAttribute("cardList", cardList);
                        // model.addAttribute("Entree", card.getEntree());
                        // model.addAttribute("Plats", card.getPlats());
                        // model.addAttribute("Desserts", card.getDesserts());
                    }
                } else {
                    model.addAttribute("cardDescription", "Aucune carte trouvée");
                }
            }


            if (firstConfig.getIs_menu() == 1) {
                System.out.println("je suis la hehehehe");
                model.addAttribute("menu", firstConfig.getIs_menu());
                long idTemplate = firstConfig.getId();
                List<Menu> menuList = menuService.findByIdSite(idTemplate);
            
                if (!menuList.isEmpty()) {
                    for (Menu menu : menuList) {
                        System.out.println("injaizdekjcz");
                        model.addAttribute("menuList", menuList);
                        // model.addAttribute("Entree", card.getEntree());
                        // model.addAttribute("Plats", card.getPlats());
                        // model.addAttribute("Desserts", card.getDesserts());
                    }
                } else {
                    model.addAttribute("menuDescription", "Aucun menu trouvée");
                }
            }


            if (firstConfig.getIs_plats() == 1) {
                System.out.println("je suis la hehehehe");
                model.addAttribute("plats", firstConfig.getIs_plats());
                long idTemplate = firstConfig.getId();
                List<Plats> platsList = platsService.findByIdSite(idTemplate);
            
                if (!platsList.isEmpty()) {
                    for (Plats plats : platsList) {
                        String photoBase64Plats = Base64.getEncoder().encodeToString(plats.getPhoto());
            // plats.setPhoto(photoBase64);
            model.addAttribute(photoBase64Plats);
                        System.out.println("injaizdekjcz");
                        model.addAttribute("platsList", platsList);
                        // model.addAttribute("Entree", card.getEntree());
                        // model.addAttribute("Plats", card.getPlats());
                        // model.addAttribute("Desserts", card.getDesserts());
                    }
                } else {
                    model.addAttribute("platDescription", "Aucun plat trouvée");
                }
            }
            
            return "template";
        } else {
            return "error";
        }
    }



    @GetMapping("/admin/{token}")
    public String getShowAdmin(@PathVariable String token, Model model) {










        Optional<FirstConfiguration> firstConfigOptional = firstConfigurationService.findByToken(token);
        if (firstConfigOptional.isPresent()) {
            FirstConfiguration firstConfig = firstConfigOptional.get();
            model.addAttribute("name", firstConfig.getName());
            model.addAttribute("url", firstConfig.getUrl());
            model.addAttribute("colorTitle", firstConfig.getColorTitle());
            model.addAttribute("colorButton", firstConfig.getColorButton());
            model.addAttribute("menu", firstConfig.getMenu());
            model.addAttribute("token", firstConfig.getToken());
           
            if (firstConfig.getCard() == 1) {
                System.out.println("je suis la hehehehe");
                model.addAttribute("card", firstConfig.getCard());
                long idTemplate = firstConfig.getId();
                List<Card> cardList = cardService.findByIdSite(idTemplate);
            
                if (!cardList.isEmpty()) {
                    for (Card card : cardList) {
                        System.out.println("injaizdekjcz");
                        model.addAttribute("cardList", cardList);
                        // model.addAttribute("Entree", card.getEntree());
                        // model.addAttribute("Plats", card.getPlats());
                        // model.addAttribute("Desserts", card.getDesserts());
                        // model.addAttribute("tokenCard", card.getTokenCard());
                    }
                } else {
                    model.addAttribute("cardDescription", "Aucune carte trouvée");
                }
            }else if(firstConfig.getCard()== 0){
                model.addAttribute("card", firstConfig.getCard());
               
            }

            if (firstConfig.getIs_menu() == 1) {
                System.out.println("je suis la hehehehe");
                model.addAttribute("menu", firstConfig.getIs_menu());
                long idTemplate = firstConfig.getId();
                List<Menu> menuList = menuService.findByIdSite(idTemplate);
            
                if (!menuList.isEmpty()) {
                    for (Menu menu : menuList) {
                        System.out.println("injaizdekjcz");
                        model.addAttribute("menuList", menuList);
                        
                        // model.addAttribute("Entree", card.getEntree());
                        // model.addAttribute("Plats", card.getPlats());
                        // model.addAttribute("Desserts", card.getDesserts());
                    }
                } else {
                    model.addAttribute("menuDescription", "Aucun menu trouvée");
                }
            }

            if (firstConfig.getIs_plats() == 1) {
                System.out.println("je suis la hehehehe");
                model.addAttribute("plats", firstConfig.getIs_plats());
                long idTemplate = firstConfig.getId();
                List<Plats> platsList = platsService.findByIdSite(idTemplate);
            
                if (!platsList.isEmpty()) {
                    for (Plats plats : platsList) {
                        System.out.println("injaizdekjcz");
                        model.addAttribute("platsList", platsList);
                        // model.addAttribute("Entree", card.getEntree());
                        // model.addAttribute("Plats", card.getPlats());
                        // model.addAttribute("Desserts", card.getDesserts());
                    }
                } else {
                    model.addAttribute("platDescription", "Aucun plat trouvée");
                }
            }
            return "admin";
        } else {
            return "error";
        }
    }


    @PostMapping("/modifyConfig/{token}")
    public String postModifyContent(
            @RequestParam String name,
            @RequestParam String url,
            @RequestParam String colorTitle,
            @RequestParam String colorButton,
            @RequestParam String menu,
            @RequestParam String password,
            @PathVariable String token,
            @RequestParam("photo") MultipartFile photoFile,
            Model model) {
    
        try {
            byte[] photoBytes = null;
            
            if (!photoFile.isEmpty()) {
                photoBytes = photoFile.getBytes();
                // System.out.println("Photo bytes: " + Arrays.toString(photoBytes));
            }
    
            FirstConfiguration updatedConfig = new FirstConfiguration(
                    name, url, colorTitle, colorButton, menu, password, token, photoBytes);
            
            Optional<FirstConfiguration> updatedConfigOptional = firstConfigurationService.updateFirstConfiguration(token, updatedConfig);
    
            if (updatedConfigOptional.isPresent()) {
                return "redirect:/config/newPage/" + updatedConfig.getToken();
            } else {
                model.addAttribute("error", "Failed to update the configuration.");
                return "error";
            }
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "An error occurred while processing the photo file.");
            return "error";
        }
    }
    
    

    @PostMapping("/createCard/{token}")
    public String createCard(
            @PathVariable String token,
            @RequestParam String Entree,
            @RequestParam String Plats,
            @RequestParam String Desserts,
            @RequestParam String colonne) {

        Optional<FirstConfiguration> configurationOptional = firstConfigurationService.findByToken(token);

          
        // String tokenCard = UUID.randomUUID().toString();
        if (configurationOptional.isPresent()) {
            FirstConfiguration configuration = configurationOptional.get();

            
            Card newCard = new Card();
            newCard.setTokenCard(UUID.randomUUID().toString());
            newCard.setEntree(Entree);
            newCard.setPlats(Plats);
            newCard.setDesserts(Desserts);
            newCard.setFirstConfiguration(configuration);

          
            cardService.saveCard(newCard);

    
            firstConfigurationService.updateCard(token);

            return "redirect:/config/newPage/" + token;
        } else {
 
            return "error";
        }
    }

    @PostMapping("/modifyCard/{token}/{tokenCard}")
    public String postModifyCard(@RequestParam String Entree,
                                 @RequestParam String Plats,
                                 @RequestParam String Desserts,
                                 @PathVariable String token,
                                 @PathVariable String tokenCard,
                                 Model model) {
        Optional<FirstConfiguration> configurationOptional = firstConfigurationService.findByToken(token);
        if (configurationOptional.isPresent()) {
            FirstConfiguration configuration = configurationOptional.get();
            long idTemplate = configuration.getId();
            Optional<Card> cardOptional = cardService.findByIdSiteSingleCard(idTemplate, tokenCard);
    
            if (cardOptional.isPresent()) {
                Card card = cardOptional.get();
                card.setEntree(Entree);
                card.setPlats(Plats);
                card.setDesserts(Desserts);
    
                cardService.saveCard(card);
            } else {
                model.addAttribute("error", "Carte non trouvée.");
                return "error";
            }
        }
        return "redirect:/config/newPage/" + token;
    }


    @PostMapping("/createMenu/{token}")
    public String createMenu(
            @PathVariable String token,
            @RequestParam String Entree,
            @RequestParam String Plats,
            @RequestParam String Desserts,
            @RequestParam String nameMenu,
            Model model) {

        Optional<FirstConfiguration> configurationOptional = firstConfigurationService.findByToken(token);
        System.out.println("je usis la dans la cinduf");
        if (configurationOptional.isPresent()) {
            FirstConfiguration configuration = configurationOptional.get();
  
            Menu newMenu = new Menu();
            newMenu.setNameMenu(nameMenu);
            newMenu.setEntree(Entree);
            newMenu.setTokenMenu(UUID.randomUUID().toString());
            newMenu.setPlats(Plats);
            newMenu.setDesserts(Desserts);
            newMenu.setFirstConfiguration(configuration);

            menuService.saveMenu(newMenu);

    
            firstConfigurationService.updateMenu(token);

            // firstConfigurationService.updateMenu(token);

            return "redirect:/config/newPage/" + token;
        } else {
            model.addAttribute("error", "Configuration non trouvée.");
            return "error";
        }
    }

    @PostMapping("/modifyMenu/{token}/{tokenMenu}")
    public String postModifyMenu(@RequestParam String Entree,
    @RequestParam String menuName,
                                 @RequestParam String Plats,
                                 @RequestParam String Desserts,
                                 @PathVariable String token,
                                 @PathVariable String tokenMenu,
                                 Model model) {
        Optional<FirstConfiguration> configurationOptional = firstConfigurationService.findByToken(token);
        if (configurationOptional.isPresent()) {
            FirstConfiguration configuration = configurationOptional.get();
            long idTemplate = configuration.getId();
            Optional<Menu> menuOptional = menuService.findByIdSiteSingleMenu(idTemplate, tokenMenu);
    
            if (menuOptional.isPresent()) {
                Menu menu = menuOptional.get();
                menu.setEntree(Entree);
                menu.setPlats(Plats);
                menu.setDesserts(Desserts);
    
                menuService.saveMenu(menu);
            } else {
                model.addAttribute("error", "Menu non trouvée.");
                return "error";
            }
        }
        return "redirect:/config/newPage/" + token;
    }



@PostMapping("/createPlat/{token}")
public String createPlat(
        @PathVariable String token,
        @RequestParam String nom,
        @RequestParam("photo") MultipartFile photoFile,
        @RequestParam String description,
        @RequestParam double prix,
        @RequestParam String allergenes,
        Model model) {

    Optional<FirstConfiguration> configurationOptional = firstConfigurationService.findByToken(token);

    if (configurationOptional.isPresent()) {
        FirstConfiguration configuration = configurationOptional.get();

        try {
            byte[] photoBytes = photoFile.getBytes();

            Plats newPlat = new Plats();
            newPlat.setNom(nom);
            newPlat.setPhoto(photoBytes);
            newPlat.setDescription(description);
            newPlat.setPrix(prix);
            newPlat.setAllergenes(allergenes); 
            newPlat.setFirstConfiguration(configuration);

            platsService.createPlat(newPlat); 

            firstConfigurationService.updatePlats(token);

            return "redirect:/config/newPage/" + token;
        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("error", "Une erreur s'est produite lors du téléchargement de la photo.");
            return "error";
        }
    } else {
        model.addAttribute("error", "Configuration non trouvée.");
        return "error";
    }
}



@PostMapping("/modifyPlat/{token}/{tokenPlat}")
public String postModifyPlat(@RequestParam String name,
                             @RequestParam MultipartFile photo, 
                             @RequestParam String description,
                             @RequestParam double prix, 
                             @RequestParam String allergenes,
                             @PathVariable String token,
                             @PathVariable String tokenPlat,
                             Model model) {
    Optional<FirstConfiguration> configurationOptional = firstConfigurationService.findByToken(token);
    if (configurationOptional.isPresent()) {
        FirstConfiguration configuration = configurationOptional.get();
        long idTemplate = configuration.getId();
        Optional<Plats> platOptional = platsService.findByIdSiteSinglePlat(idTemplate, tokenPlat);

        if (platOptional.isPresent()) {
            Plats plat = platOptional.get();
            plat.setNom(name);
            try {
                plat.setPhoto(photo.getBytes());
            } catch (IOException e) {
                model.addAttribute("error", "Erreur lors du téléchargement de la photo.");
                return "error";
            }
            plat.setDescription(description);
            plat.setPrix(prix);
            plat.setAllergenes(allergenes);

            platsService.updatePlat(idTemplate, plat);
        } else {
            model.addAttribute("error", "Plat non trouvé.");
            return "error";
        }
    } else {
        model.addAttribute("error", "Configuration non trouvée.");
        return "error";
    }
    return "redirect:/config/newPage/" + token;
}





@PostMapping("/deleteCard/{token}/{tokenCard}")
public String deleteCard(@PathVariable String token, @PathVariable String tokenCard, Model model) {
    try {
        Optional<FirstConfiguration> configOptional = firstConfigurationService.findByToken(token);
        if (configOptional.isPresent()) {
            FirstConfiguration configuration = configOptional.get();
            Optional<Card> cardOptional = cardService.findByIdSiteSingleCard(configuration.getId(), tokenCard);
            if (cardOptional.isPresent()) {
                cardService.deleteCard(cardOptional.get().getId());
                return "redirect:/config/newPage/" + token;
            } else {
                model.addAttribute("error", "Carte non trouvée.");
                return "error";
            }
        } else {
            model.addAttribute("error", "Configuration non trouvée.");
            return "error";
        }
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "Une erreur s'est produite lors de la suppression de la carte.");
        return "error";
    }
}

@PostMapping("/deleteMenu/{token}/{tokenMenu}")
public String deleteMenu(@PathVariable String token, @PathVariable String tokenMenu, Model model) {
    try {
        Optional<FirstConfiguration> configOptional = firstConfigurationService.findByToken(token);
        if (configOptional.isPresent()) {
            FirstConfiguration configuration = configOptional.get();
            Optional<Menu> menuOptional = menuService.findByIdSiteSingleMenu(configuration.getId(), tokenMenu);
            if (menuOptional.isPresent()) {
                menuService.deleteMenu(menuOptional.get().getId());
                return "redirect:/config/newPage/" + token;
            } else {
                model.addAttribute("error", "Menu non trouvé.");
                return "error";
            }
        } else {
            model.addAttribute("error", "Configuration non trouvée.");
            return "error";
        }
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "Une erreur s'est produite lors de la suppression du menu.");
        return "error";
    }
}

@PostMapping("/deletePlat/{token}/{tokenPlat}")
public String deletePlat(@PathVariable String token, @PathVariable String tokenPlat, Model model) {
    try {
        Optional<FirstConfiguration> configOptional = firstConfigurationService.findByToken(token);
        if (configOptional.isPresent()) {
            FirstConfiguration configuration = configOptional.get();
            Optional<Plats> platOptional = platsService.findByIdSiteSinglePlat(configuration.getId(), tokenPlat);
            if (platOptional.isPresent()) {
                platsService.deletePlat(platOptional.get().getId());
                return "redirect:/config/newPage/" + token;
            } else {
                model.addAttribute("error", "Plat non trouvé.");
                return "error";
            }
        } else {
            model.addAttribute("error", "Configuration non trouvée.");
            return "error";
        }
    } catch (Exception e) {
        e.printStackTrace();
        model.addAttribute("error", "Une erreur s'est produite lors de la suppression du plat.");
        return "error";
    }
}



}

    
    

