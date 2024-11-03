import { StatusBar } from 'expo-status-bar';
import { TouchableOpacity, StyleSheet, Text, View, Image } from 'react-native';


export default function Page_de_demarrage ({navigation}) {
  return (

    
    <View style={styles.container}>
      <Image
        source={require('../assets/snapchat-logo-snapchat-icon-transparent-free-png.webp')}
        style={{ width: 150, height: 150 }}
      />
      <View style={styles.buttonContainer}>
        <TouchableOpacity style={[styles.button, styles.connexionButton]} onPress={() => navigation.navigate("Connexion")}>
          <Text style={styles.buttonText}>CONNEXION</Text>
        </TouchableOpacity>
        <TouchableOpacity style={[styles.button, styles.inscriptionButton]} onPress={() => navigation.navigate("Inscription")}>
          <Text style={styles.buttonText}>INSCRIPTION</Text>
          
        </TouchableOpacity>
      </View>

      <StatusBar style="auto" />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: 'yellow',
    alignItems: 'center',
    justifyContent: 'center',
    position: 'relative',
  },
  text: {
    fontSize: 20,
    marginBottom: 20,
  },
  buttonContainer: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    width: '100%',
    flexDirection: 'column',
    justifyContent: 'space-around',
  },
  button: {
    alignItems: 'center',
    justifyContent: 'center',
    height: 60,
  },
  buttonText: {
    color: 'white',
    fontSize:28,
  },
  connexionButton: {
    backgroundColor: '#eb344c',
    height: 80,
  },
  inscriptionButton: {
    backgroundColor: '#34c0eb',
    height: 80,
    
  },
});




















/*
import React, { useEffect, useState } from "react";
import { ScrollView, StyleSheet, Text, View } from "react-native";
import Inscription from "./Inscription";

export default function Page_de_demarrage({ navigation }) {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getUsers();
  }, []);

  const getUsers = () => {
    fetch("https://mysnapchat.epidoc.eu/user", {
      headers: {
        Accept: "application/json",
        Authorization: "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6ImdhamFucmFqLm1vaGFuYXJhakBlcGl0ZWNoLmV1IiwiaWQiOiI2NDkxN2E0N2ZiODM4NDAwNzg1NzI0MGIiLCJpYXQiOjE2ODcyNTU3MDIsImV4cCI6MTY4NzM0MjEwMn0.ug-s2OVVhNbSmaS8kEO33rzqEXEf7uPRAiq69JDZDRA"
      }
    })
      .then(res => res.json())
      .then(result => setUsers(result.data))
      .catch(error => console.log(error));
  };

  return (
    <View style={styles.container}>
      <ScrollView contentContainerStyle={styles.scrollContainer}>
        {users.map(user => (
          <View style={styles.userContainer} key={user._id}>
            <Text style={styles.username}>{user.username}</Text>
            <Text style={styles.userId}>{user._id}</Text>
            

            
          

          </View>
        ))}
      </ScrollView>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "yellow",
    alignItems: "center",
    justifyContent: "center",
    position: "relative"
  },
  scrollContainer: {
    alignItems: "center",
    justifyContent: "center",
    paddingVertical: 20
  },
  userContainer: {
    backgroundColor: "#fff",
    borderRadius: 10,
    padding: 10,
    marginBottom: 10
  },
  username: {
    fontSize: 16,
    fontWeight: "bold"
  },
  userId: {
    fontSize: 14,
    color: "#999"
  }
});

*/

