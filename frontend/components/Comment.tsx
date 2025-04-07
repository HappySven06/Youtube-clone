import { Text, View, Image, Platform, Pressable } from 'react-native';
import { Link } from 'expo-router';
import React from 'react';

import AntDesign from '@expo/vector-icons/AntDesign';

interface props {
  userId: number,
  userName: string,
  userPicLink: string,
  comment: string
}

export default function Comment ({userId, userName, userPicLink, comment} : props){
  const mediaUrl = process.env.EXPO_PUBLIC_MEDIA_URL;
  return(
    <View className='flex flex-row rounded-md shadow-md mb-3'>
      <Image source={{ uri: mediaUrl + userPicLink }} className='m-2 rounded-md' style={{width: 50, height: 50}} />
      <View>
        <Text className='text-white my-2'>{userName}</Text>
        <Text className='text-white text-sm mb-1'>{comment}</Text>
      </View>
    </View>
  )
}