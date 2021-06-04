import * as React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';

import SelectScreen from './SelectScreen';
import LoginScreen from './LoginScreen';
import LinkScreen from './LinkScreen';

export type AppStackParamList = {
  SelectScreen: {};
  LoginScreen: {};
  LinkScreen: {};
};
export interface TAppStackProps {}
const AppStack = createStackNavigator<AppStackParamList>();
const AppStackScreen = (_props: TAppStackProps) => (
  <AppStack.Navigator>
    <AppStack.Screen
      name={'SelectScreen'}
      component={SelectScreen}
      options={{ headerTitle: 'Examples' }}
    />
    <AppStack.Screen
      name={'LoginScreen'}
      component={LoginScreen}
      options={{ headerTitle: 'Login' }}
    />
    <AppStack.Screen
      name={'LinkScreen'}
      component={LinkScreen}
      options={{ headerTitle: 'Link' }}
    />
  </AppStack.Navigator>
);

export default function App() {
  return (
    <NavigationContainer>
      <AppStackScreen />
    </NavigationContainer>
  );
}
