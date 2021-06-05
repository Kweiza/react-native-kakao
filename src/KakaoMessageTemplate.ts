export type TKakaoMessageLink = {
  web_url?: string;
  mobile_web_url?: string;
  android_execution_params?: { [key: string]: string };
  ios_execution_params?: { [key: string]: string };
};

export type TKakaoMessageContent = {
  title: string;
  image_url: string;
  image_width?: number;
  image_height?: number;
  description?: string;
  link: TKakaoMessageLink;
};

export type TKakaoMessageSocial = {
  like_count?: number;
  comment_count?: number;
  shared_count?: number;
  view_count?: number;
  subscriber_count?: number;
};

export type TKakaoMessageButton = {
  title: string;
  link: TKakaoMessageLink;
};

export type TKakaoMessageCommerce = {
  product_name?: string;
  regular_price: number;
  discount_price?: number;
  discount_rate?: number;
  fixed_discount_price?: number;
};

export type TKakaoMessageTemplateFeed = {
  object_type: 'feed';
  content: TKakaoMessageContent;
  social?: TKakaoMessageSocial;
  button_title?: string;
  buttons?: TKakaoMessageButton[];
};

export type TKakaoMessageTemplateList = {
  object_type: 'list';
  header_title: string;
  header_link: TKakaoMessageLink;
  contents: TKakaoMessageContent[];
  button_title?: string;
  buttons?: TKakaoMessageButton[];
};

export type TKakaoMessageTemplateLocation = {
  object_type: 'location';
  address: string;
  address_title?: string;
  content: TKakaoMessageContent;
  social?: TKakaoMessageSocial;
  button_title?: string;
  buttons?: TKakaoMessageButton[];
};

export type TKakaoMessageTemplateCommerce = {
  object_type: 'commerce';
  content: TKakaoMessageContent;
  commerce?: TKakaoMessageCommerce;
  button_title?: string;
  buttons?: TKakaoMessageButton[];
};

export type TKakaoMessageTemplateText = {
  object_type: 'text';
  text: string;
  link: TKakaoMessageLink;
  button_title?: string;
  buttons?: TKakaoMessageButton[];
};
