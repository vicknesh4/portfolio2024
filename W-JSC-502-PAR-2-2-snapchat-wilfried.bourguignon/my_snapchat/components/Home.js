import React, { useState, useEffect, useRef } from 'react';
import { ImageBackground, Image } from 'react-native';
import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TouchableOpacity } from 'react-native';
import { Camera } from 'expo-camera';
import * as ImagePicker from 'expo-image-picker';
import { useNavigation } from '@react-navigation/native';

export default function Home() {
  const [cameraPermission, setCameraPermission] = useState(null);
  const [showCamera, setShowCamera] = useState(false);
  const [selectedImage, setSelectedImage] = useState(null);
  const cameraRef = useRef(null);
  const navigation = useNavigation();

  useEffect(() => {
    (async () => {
      const { status } = await Camera.requestCameraPermissionsAsync();
      setCameraPermission(status === 'granted');
    })();
  }, []);

  const handleImagePickerPress = async () => {
    const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
    if (status === 'granted') {
      const result = await ImagePicker.launchImageLibraryAsync();
      if (!result.cancelled) {
        const selectedImageUri = result.uri;
        setSelectedImage(selectedImageUri);
      }
    } else {
      console.log('Autorisation de la bibliothèque multimédia refusée');
    }
  };

  const handleCameraPress = async () => {
    if (cameraPermission) {
      const { status } = await Camera.getPermissionsAsync();
      if (status === 'granted') {
        setShowCamera(true);
      }
    }
  };

  const handleBackPress = () => {
    setShowCamera(false);
  };

  const handleTakePhotoPress = async () => {
    if (cameraRef.current) {
      const photo = await cameraRef.current.takePictureAsync();
      setSelectedImage(photo.uri);
    }
  };

  const handleChatPress = () => {
    navigation.navigate('Chat'); // Remplacez 'Chat' par le nom réel de votre écran de discussions
  };

  if (selectedImage) {
    return (
      <View style={styles.container}>
        <Image source={{ uri: selectedImage }} style={styles.selectedImage} />
        {/* Autres composants ou actions ici */}
      </View>
    );
  }

  if (showCamera) {
    return (
      <View style={styles.container}>
        <Camera style={styles.camera} ref={cameraRef}>
          <ImageBackground source={{ uri: 'VOTRE_SOURCE_VIDÉO' }} style={styles.cameraBackground} />
        </Camera>
        <TouchableOpacity style={styles.backButton} onPress={handleBackPress}>
          <Text style={styles.backButtonText}>Retour</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.captureButton} onPress={handleTakePhotoPress}>
          <Text style={styles.captureButtonText}>Prendre une photo</Text>
        </TouchableOpacity>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text>Test Benjamin Snap</Text>
      <StatusBar style="auto" />
      <View style={styles.box1}>
        <TouchableOpacity style={styles.button} onPress={handleCameraPress}>
          <Text style={styles.buttonText}>Activer la caméra</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.box2}>
        <TouchableOpacity style={styles.button} onPress={handleImagePickerPress}>
          <Text style={styles.buttonText}>Sélectionner une image</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.box3}>
        <TouchableOpacity style={styles.button} onPress={() => console.log("Bouton 3 pressé")}>
          <Text style={styles.buttonText}>Test 3</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.buttonContainer}>
        <TouchableOpacity style={styles.button} onPress={handleChatPress}>
          <Text style={styles.buttonText}>Discussions</Text>
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#000',
    paddingTop: 50,
    paddingHorizontal: 20,
  },
  text: {
    color: '#fff',
    fontSize: 20,
    fontWeight: 'bold',
    marginBottom: 20,
  },
  cameraContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  camera: {
    width: '100%',
    height: '100%',
  },
  backButton: {
    position: 'absolute',
    top: 50,
    left: 20,
    backgroundColor: 'rgba(0, 0, 0, 0.5)',
    borderRadius: 20,
    padding: 10,
  },
  backButtonText: {
    color: '#fff',
    fontSize: 16,
  },
  buttonContainer: {
    flexDirection: 'row',
    justifyContent: 'center',
    marginBottom: 20,
  },
  button: {
    width: 60,
    height: 60,
    backgroundColor: '#fff',
    borderRadius: 30,
    marginHorizontal: 10,
    alignItems: 'center',
    justifyContent: 'center',
  },
  buttonText: {
    color: '#000',
    fontSize: 12,
  },
});
