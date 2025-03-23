import { Text, View, Platform, Image, Pressable } from 'react-native';
import { Link } from 'expo-router';
import React from 'react';

import Ionicons from '@expo/vector-icons/Ionicons';

interface props {
  id: number,
  name: string,
  thumbnail: string,
  channel: string,
  views: string,
  color: string,
}

export default function VideoCard ({id, name, thumbnail, channel, views, color} : props){
  return(
    <Link href={{ pathname: '/(stack)/watch/[watch]', params: { watch: id }}} className='flex flex-col justify-between rounded-md shadow-md' style={{ width: '100%' }} asChild>
      <Pressable>
        <Image source={{ uri: thumbnail }} style={{width: '100%', height: 200}} />
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
      </Pressable>
    </Link>
  )
}