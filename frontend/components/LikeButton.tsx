import { Text, View, Platform, Pressable } from 'react-native';
import { Link } from 'expo-router';
import React from 'react';

import AntDesign from '@expo/vector-icons/AntDesign';

interface props {
  posLike: number | undefined,
  negLike: number | undefined
}

export default function LikeButton ({posLike, negLike} : props){
  return(
    <View className='flex flex-row rounded-md shadow-md'>
      <Pressable className='flex flex-row mr-2'>
        <AntDesign name="like2" size={24} color="white" />
        <Text className='text-white text-sm'>{posLike}</Text>
      </Pressable>
      <Pressable className='flex flex-row'>
        <AntDesign name="dislike2" size={24} color="white" />
        <Text className='text-white text-sm text-center'>{negLike}</Text>
      </Pressable>
    </View>
  )
}