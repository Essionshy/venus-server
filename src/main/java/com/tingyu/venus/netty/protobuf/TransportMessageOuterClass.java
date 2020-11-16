package com.tingyu.venus.netty.protobuf;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: TransportMessage.proto

public final class TransportMessageOuterClass {
  private TransportMessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code MessageType}
   */
  public enum MessageType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <pre>
     *0代表序列号
     * </pre>
     *
     * <code>UserAuthenticationNotice = 0;</code>
     */
    UserAuthenticationNotice(0),
    /**
     * <pre>
     *用户强制下线通知
     * </pre>
     *
     * <code>UserForceOffLineNotice = 1;</code>
     */
    UserForceOffLineNotice(1),
    /**
     * <pre>
     *用户登录过期通知
     * </pre>
     *
     * <code>UserTokenExpireNotice = 2;</code>
     */
    UserTokenExpireNotice(2),
    UNRECOGNIZED(-1),
    ;

    /**
     * <pre>
     *0代表序列号
     * </pre>
     *
     * <code>UserAuthenticationNotice = 0;</code>
     */
    public static final int UserAuthenticationNotice_VALUE = 0;
    /**
     * <pre>
     *用户强制下线通知
     * </pre>
     *
     * <code>UserForceOffLineNotice = 1;</code>
     */
    public static final int UserForceOffLineNotice_VALUE = 1;
    /**
     * <pre>
     *用户登录过期通知
     * </pre>
     *
     * <code>UserTokenExpireNotice = 2;</code>
     */
    public static final int UserTokenExpireNotice_VALUE = 2;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static MessageType valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static MessageType forNumber(int value) {
      switch (value) {
        case 0: return UserAuthenticationNotice;
        case 1: return UserForceOffLineNotice;
        case 2: return UserTokenExpireNotice;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MessageType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        MessageType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MessageType>() {
            public MessageType findValueByNumber(int number) {
              return MessageType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return TransportMessageOuterClass.getDescriptor().getEnumTypes().get(0);
    }

    private static final MessageType[] VALUES = values();

    public static MessageType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private MessageType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:MessageType)
  }

  public interface TransportMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:TransportMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int64 messageId = 1;</code>
     * @return The messageId.
     */
    long getMessageId();

    /**
     * <code>.MessageType messageType = 2;</code>
     * @return The enum numeric value on the wire for messageType.
     */
    int getMessageTypeValue();
    /**
     * <code>.MessageType messageType = 2;</code>
     * @return The messageType.
     */
    TransportMessageOuterClass.MessageType getMessageType();

    /**
     * <code>.google.protobuf.Any messageBody = 3;</code>
     * @return Whether the messageBody field is set.
     */
    boolean hasMessageBody();
    /**
     * <code>.google.protobuf.Any messageBody = 3;</code>
     * @return The messageBody.
     */
    com.google.protobuf.Any getMessageBody();
    /**
     * <code>.google.protobuf.Any messageBody = 3;</code>
     */
    com.google.protobuf.AnyOrBuilder getMessageBodyOrBuilder();
  }
  /**
   * Protobuf type {@code TransportMessage}
   */
  public static final class TransportMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:TransportMessage)
      TransportMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use TransportMessage.newBuilder() to construct.
    private TransportMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private TransportMessage() {
      messageType_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new TransportMessage();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private TransportMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {

              messageId_ = input.readInt64();
              break;
            }
            case 16: {
              int rawValue = input.readEnum();

              messageType_ = rawValue;
              break;
            }
            case 26: {
              com.google.protobuf.Any.Builder subBuilder = null;
              if (messageBody_ != null) {
                subBuilder = messageBody_.toBuilder();
              }
              messageBody_ = input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(messageBody_);
                messageBody_ = subBuilder.buildPartial();
              }

              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return TransportMessageOuterClass.internal_static_TransportMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return TransportMessageOuterClass.internal_static_TransportMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              TransportMessageOuterClass.TransportMessage.class, TransportMessageOuterClass.TransportMessage.Builder.class);
    }

    public static final int MESSAGEID_FIELD_NUMBER = 1;
    private long messageId_;
    /**
     * <code>int64 messageId = 1;</code>
     * @return The messageId.
     */
    @java.lang.Override
    public long getMessageId() {
      return messageId_;
    }

    public static final int MESSAGETYPE_FIELD_NUMBER = 2;
    private int messageType_;
    /**
     * <code>.MessageType messageType = 2;</code>
     * @return The enum numeric value on the wire for messageType.
     */
    @java.lang.Override public int getMessageTypeValue() {
      return messageType_;
    }
    /**
     * <code>.MessageType messageType = 2;</code>
     * @return The messageType.
     */
    @java.lang.Override public TransportMessageOuterClass.MessageType getMessageType() {
      @SuppressWarnings("deprecation")
      TransportMessageOuterClass.MessageType result = TransportMessageOuterClass.MessageType.valueOf(messageType_);
      return result == null ? TransportMessageOuterClass.MessageType.UNRECOGNIZED : result;
    }

    public static final int MESSAGEBODY_FIELD_NUMBER = 3;
    private com.google.protobuf.Any messageBody_;
    /**
     * <code>.google.protobuf.Any messageBody = 3;</code>
     * @return Whether the messageBody field is set.
     */
    @java.lang.Override
    public boolean hasMessageBody() {
      return messageBody_ != null;
    }
    /**
     * <code>.google.protobuf.Any messageBody = 3;</code>
     * @return The messageBody.
     */
    @java.lang.Override
    public com.google.protobuf.Any getMessageBody() {
      return messageBody_ == null ? com.google.protobuf.Any.getDefaultInstance() : messageBody_;
    }
    /**
     * <code>.google.protobuf.Any messageBody = 3;</code>
     */
    @java.lang.Override
    public com.google.protobuf.AnyOrBuilder getMessageBodyOrBuilder() {
      return getMessageBody();
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (messageId_ != 0L) {
        output.writeInt64(1, messageId_);
      }
      if (messageType_ != TransportMessageOuterClass.MessageType.UserAuthenticationNotice.getNumber()) {
        output.writeEnum(2, messageType_);
      }
      if (messageBody_ != null) {
        output.writeMessage(3, getMessageBody());
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (messageId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, messageId_);
      }
      if (messageType_ != TransportMessageOuterClass.MessageType.UserAuthenticationNotice.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, messageType_);
      }
      if (messageBody_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(3, getMessageBody());
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof TransportMessageOuterClass.TransportMessage)) {
        return super.equals(obj);
      }
      TransportMessageOuterClass.TransportMessage other = (TransportMessageOuterClass.TransportMessage) obj;

      if (getMessageId()
          != other.getMessageId()) return false;
      if (messageType_ != other.messageType_) return false;
      if (hasMessageBody() != other.hasMessageBody()) return false;
      if (hasMessageBody()) {
        if (!getMessageBody()
            .equals(other.getMessageBody())) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + MESSAGEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getMessageId());
      hash = (37 * hash) + MESSAGETYPE_FIELD_NUMBER;
      hash = (53 * hash) + messageType_;
      if (hasMessageBody()) {
        hash = (37 * hash) + MESSAGEBODY_FIELD_NUMBER;
        hash = (53 * hash) + getMessageBody().hashCode();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static TransportMessageOuterClass.TransportMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static TransportMessageOuterClass.TransportMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static TransportMessageOuterClass.TransportMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static TransportMessageOuterClass.TransportMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(TransportMessageOuterClass.TransportMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code TransportMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:TransportMessage)
        TransportMessageOuterClass.TransportMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return TransportMessageOuterClass.internal_static_TransportMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return TransportMessageOuterClass.internal_static_TransportMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                TransportMessageOuterClass.TransportMessage.class, TransportMessageOuterClass.TransportMessage.Builder.class);
      }

      // Construct using TransportMessageOuterClass.TransportMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        messageId_ = 0L;

        messageType_ = 0;

        if (messageBodyBuilder_ == null) {
          messageBody_ = null;
        } else {
          messageBody_ = null;
          messageBodyBuilder_ = null;
        }
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return TransportMessageOuterClass.internal_static_TransportMessage_descriptor;
      }

      @java.lang.Override
      public TransportMessageOuterClass.TransportMessage getDefaultInstanceForType() {
        return TransportMessageOuterClass.TransportMessage.getDefaultInstance();
      }

      @java.lang.Override
      public TransportMessageOuterClass.TransportMessage build() {
        TransportMessageOuterClass.TransportMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public TransportMessageOuterClass.TransportMessage buildPartial() {
        TransportMessageOuterClass.TransportMessage result = new TransportMessageOuterClass.TransportMessage(this);
        result.messageId_ = messageId_;
        result.messageType_ = messageType_;
        if (messageBodyBuilder_ == null) {
          result.messageBody_ = messageBody_;
        } else {
          result.messageBody_ = messageBodyBuilder_.build();
        }
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof TransportMessageOuterClass.TransportMessage) {
          return mergeFrom((TransportMessageOuterClass.TransportMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(TransportMessageOuterClass.TransportMessage other) {
        if (other == TransportMessageOuterClass.TransportMessage.getDefaultInstance()) return this;
        if (other.getMessageId() != 0L) {
          setMessageId(other.getMessageId());
        }
        if (other.messageType_ != 0) {
          setMessageTypeValue(other.getMessageTypeValue());
        }
        if (other.hasMessageBody()) {
          mergeMessageBody(other.getMessageBody());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        TransportMessageOuterClass.TransportMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (TransportMessageOuterClass.TransportMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private long messageId_ ;
      /**
       * <code>int64 messageId = 1;</code>
       * @return The messageId.
       */
      @java.lang.Override
      public long getMessageId() {
        return messageId_;
      }
      /**
       * <code>int64 messageId = 1;</code>
       * @param value The messageId to set.
       * @return This builder for chaining.
       */
      public Builder setMessageId(long value) {
        
        messageId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 messageId = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageId() {
        
        messageId_ = 0L;
        onChanged();
        return this;
      }

      private int messageType_ = 0;
      /**
       * <code>.MessageType messageType = 2;</code>
       * @return The enum numeric value on the wire for messageType.
       */
      @java.lang.Override public int getMessageTypeValue() {
        return messageType_;
      }
      /**
       * <code>.MessageType messageType = 2;</code>
       * @param value The enum numeric value on the wire for messageType to set.
       * @return This builder for chaining.
       */
      public Builder setMessageTypeValue(int value) {
        
        messageType_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.MessageType messageType = 2;</code>
       * @return The messageType.
       */
      @java.lang.Override
      public TransportMessageOuterClass.MessageType getMessageType() {
        @SuppressWarnings("deprecation")
        TransportMessageOuterClass.MessageType result = TransportMessageOuterClass.MessageType.valueOf(messageType_);
        return result == null ? TransportMessageOuterClass.MessageType.UNRECOGNIZED : result;
      }
      /**
       * <code>.MessageType messageType = 2;</code>
       * @param value The messageType to set.
       * @return This builder for chaining.
       */
      public Builder setMessageType(TransportMessageOuterClass.MessageType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        messageType_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.MessageType messageType = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessageType() {
        
        messageType_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.Any messageBody_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> messageBodyBuilder_;
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       * @return Whether the messageBody field is set.
       */
      public boolean hasMessageBody() {
        return messageBodyBuilder_ != null || messageBody_ != null;
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       * @return The messageBody.
       */
      public com.google.protobuf.Any getMessageBody() {
        if (messageBodyBuilder_ == null) {
          return messageBody_ == null ? com.google.protobuf.Any.getDefaultInstance() : messageBody_;
        } else {
          return messageBodyBuilder_.getMessage();
        }
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       */
      public Builder setMessageBody(com.google.protobuf.Any value) {
        if (messageBodyBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          messageBody_ = value;
          onChanged();
        } else {
          messageBodyBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       */
      public Builder setMessageBody(
          com.google.protobuf.Any.Builder builderForValue) {
        if (messageBodyBuilder_ == null) {
          messageBody_ = builderForValue.build();
          onChanged();
        } else {
          messageBodyBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       */
      public Builder mergeMessageBody(com.google.protobuf.Any value) {
        if (messageBodyBuilder_ == null) {
          if (messageBody_ != null) {
            messageBody_ =
              com.google.protobuf.Any.newBuilder(messageBody_).mergeFrom(value).buildPartial();
          } else {
            messageBody_ = value;
          }
          onChanged();
        } else {
          messageBodyBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       */
      public Builder clearMessageBody() {
        if (messageBodyBuilder_ == null) {
          messageBody_ = null;
          onChanged();
        } else {
          messageBody_ = null;
          messageBodyBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       */
      public com.google.protobuf.Any.Builder getMessageBodyBuilder() {
        
        onChanged();
        return getMessageBodyFieldBuilder().getBuilder();
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       */
      public com.google.protobuf.AnyOrBuilder getMessageBodyOrBuilder() {
        if (messageBodyBuilder_ != null) {
          return messageBodyBuilder_.getMessageOrBuilder();
        } else {
          return messageBody_ == null ?
              com.google.protobuf.Any.getDefaultInstance() : messageBody_;
        }
      }
      /**
       * <code>.google.protobuf.Any messageBody = 3;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
          getMessageBodyFieldBuilder() {
        if (messageBodyBuilder_ == null) {
          messageBodyBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                  getMessageBody(),
                  getParentForChildren(),
                  isClean());
          messageBody_ = null;
        }
        return messageBodyBuilder_;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:TransportMessage)
    }

    // @@protoc_insertion_point(class_scope:TransportMessage)
    private static final TransportMessageOuterClass.TransportMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new TransportMessageOuterClass.TransportMessage();
    }

    public static TransportMessageOuterClass.TransportMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<TransportMessage>
        PARSER = new com.google.protobuf.AbstractParser<TransportMessage>() {
      @java.lang.Override
      public TransportMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new TransportMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<TransportMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<TransportMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public TransportMessageOuterClass.TransportMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TransportMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TransportMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026TransportMessage.proto\032\031google/protobu" +
      "f/any.proto\"s\n\020TransportMessage\022\021\n\tmessa" +
      "geId\030\001 \001(\003\022!\n\013messageType\030\002 \001(\0162\014.Messag" +
      "eType\022)\n\013messageBody\030\003 \001(\0132\024.google.prot" +
      "obuf.Any*b\n\013MessageType\022\034\n\030UserAuthentic" +
      "ationNotice\020\000\022\032\n\026UserForceOffLineNotice\020" +
      "\001\022\031\n\025UserTokenExpireNotice\020\002B\036B\032Transpor" +
      "tMessageOuterClassH\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.AnyProto.getDescriptor(),
        });
    internal_static_TransportMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_TransportMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TransportMessage_descriptor,
        new java.lang.String[] { "MessageId", "MessageType", "MessageBody", });
    com.google.protobuf.AnyProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
