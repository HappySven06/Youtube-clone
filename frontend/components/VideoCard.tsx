import { Text, View, Platform, Image } from 'react-native';
import { Link } from 'expo-router';
import React from 'react';

import Ionicons from '@expo/vector-icons/Ionicons';

interface props {
  name: string,
  channel: string,
  views: string,
  color: string,
}

export default function VideoCard ({name, channel, views, color} : props){
  return(
    <View className='flex flex-col justify-between rounded-md shadow-md' style={{ width: '100%' }}>
      <Image source={{ uri: 'https://reactnative.dev/docs/assets/p_cat2.png' }} style={{width: '100%', height: 200}} />
      <View className='m-5'>
        <View className='flex flex-row justify-between'>
          <View>
            <Text className='text-white text-2xl font-medium'>{name}</Text>
          </View>
          <View>
            <Ionicons name="ellipsis-vertical" size={24} color="white" />
          </View>
        </View>
        <View>
          <Text className='text-white text-sm'>{channel} - {views}</Text>
        </View>
      </View>
    </View>
  )
}