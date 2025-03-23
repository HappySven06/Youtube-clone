import { Text, View, Platform, Pressable } from 'react-native';
import { Link } from 'expo-router';
import React from 'react';

import Ionicons from '@expo/vector-icons/Ionicons';

export default function HeaderLabel (){
  return(
    <View className='flex flex-row justify-between' style={{ width: '100%' }}>
      <View className='flex flex-row space-x-2 self-start'>
        {Platform.OS === 'web' && 
          <Ionicons name="menu" size={24} color="white" />
        }
        <Link href='/(tabs)' asChild>
          <Pressable>
            <Text className='text-xl text-white font-semibold'>Youtube</Text>
          </Pressable>
        </Link>
      </View>

      <View className='flex flex-row space-x-2 self-end'>
        {(Platform.OS === 'android' || Platform.OS === 'ios') && (
          <Ionicons name="search" size={24} color="white" />
        )}
        <Ionicons name="notifications-outline" size={24} color="white" />
        {Platform.OS === 'web' &&
          <Ionicons name="person-circle-outline" size={24} color="white" />
        }
      </View>
    </View>
  )
}