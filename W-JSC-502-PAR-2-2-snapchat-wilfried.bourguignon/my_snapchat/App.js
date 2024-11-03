import React from "react";
import Inscription from "./components/Inscription";
import Page_de_demarrage from './components/Page_de_demarrage';
import Connexion from './components/Connexion'
import Home from './components/Home'

import {createNativeStackNavigator} from "@react-navigation/native-stack";
import { NavigationContainer } from "@react-navigation/native";

const Stack = createNativeStackNavigator();


export default function App(){

    return(
      <NavigationContainer>
        <Stack.Navigator>
          <Stack.Screen
          name="page_de_demarrage"
          component={Page_de_demarrage}
          options={{ headerShown: false }} // Masquer le titre de l'écran
          
          />
          <Stack.Screen 
          name ="Inscription"
          component ={Inscription}
          options={{ headerShown: false }} // Masquer le titre de l'écran

            />

          <Stack.Screen 
          name ="Connexion"
          component ={Connexion}
          options={{ headerShown: false }} // Masquer le titre de l'écran

            />
            <Stack.Screen 
          name ="Home"
          component ={Home}
          options={{ headerShown: false }} // Masquer le titre de l'écran

            />
        </Stack.Navigator>

      </NavigationContainer>
    
    );
  }

