package com.tingyu.venus.netty.protobuf;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: UserAuthentication.proto

public final class UserAuthenticationNotice {
  private UserAuthenticationNotice() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface UserAuthenticationMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:UserAuthenticationMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 userId = 1;</code>
     * @return The userId.
     */
    int getUserId();

    /**
     * <code>string username = 2;</code>
     * @return The username.
     */
    java.lang.String getUsername();
    /**
     * <code>string username = 2;</code>
     * @return The bytes for username.
     */
    com.google.protobuf.ByteString
        getUsernameBytes();
  }
  /**
   * Protobuf type {@code UserAuthenticationMessage}
   */
  public static final class UserAuthenticationMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:UserAuthenticationMessage)
      UserAuthenticationMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use UserAuthenticationMessage.newBuilder() to construct.
    private UserAuthenticationMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private UserAuthenticationMessage() {
      username_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new UserAuthenticationMessage();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private UserAuthenticationMessage(
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

              userId_ = input.readInt32();
              break;
            }
            case 18: {
              java.lang.String s = input.readStringRequireUtf8();

              username_ = s;
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
      return UserAuthenticationNotice.internal_static_UserAuthenticationMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return UserAuthenticationNotice.internal_static_UserAuthenticationMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UserAuthenticationNotice.UserAuthenticationMessage.class, UserAuthenticationNotice.UserAuthenticationMessage.Builder.class);
    }

    public static final int USERID_FIELD_NUMBER = 1;
    private int userId_;
    /**
     * <code>int32 userId = 1;</code>
     * @return The userId.
     */
    @java.lang.Override
    public int getUserId() {
      return userId_;
    }

    public static final int USERNAME_FIELD_NUMBER = 2;
    private volatile java.lang.Object username_;
    /**
     * <code>string username = 2;</code>
     * @return The username.
     */
    @java.lang.Override
    public java.lang.String getUsername() {
      java.lang.Object ref = username_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        username_ = s;
        return s;
      }
    }
    /**
     * <code>string username = 2;</code>
     * @return The bytes for username.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getUsernameBytes() {
      java.lang.Object ref = username_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        username_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
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
      if (userId_ != 0) {
        output.writeInt32(1, userId_);
      }
      if (!getUsernameBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 2, username_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (userId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, userId_);
      }
      if (!getUsernameBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, username_);
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
      if (!(obj instanceof UserAuthenticationNotice.UserAuthenticationMessage)) {
        return super.equals(obj);
      }
      UserAuthenticationNotice.UserAuthenticationMessage other = (UserAuthenticationNotice.UserAuthenticationMessage) obj;

      if (getUserId()
          != other.getUserId()) return false;
      if (!getUsername()
          .equals(other.getUsername())) return false;
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
      hash = (37 * hash) + USERID_FIELD_NUMBER;
      hash = (53 * hash) + getUserId();
      hash = (37 * hash) + USERNAME_FIELD_NUMBER;
      hash = (53 * hash) + getUsername().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static UserAuthenticationNotice.UserAuthenticationMessage parseFrom(
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
    public static Builder newBuilder(UserAuthenticationNotice.UserAuthenticationMessage prototype) {
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
     * Protobuf type {@code UserAuthenticationMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:UserAuthenticationMessage)
        UserAuthenticationNotice.UserAuthenticationMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return UserAuthenticationNotice.internal_static_UserAuthenticationMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return UserAuthenticationNotice.internal_static_UserAuthenticationMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                UserAuthenticationNotice.UserAuthenticationMessage.class, UserAuthenticationNotice.UserAuthenticationMessage.Builder.class);
      }

      // Construct using UserAuthenticationNotice.UserAuthenticationMessage.newBuilder()
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
        userId_ = 0;

        username_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return UserAuthenticationNotice.internal_static_UserAuthenticationMessage_descriptor;
      }

      @java.lang.Override
      public UserAuthenticationNotice.UserAuthenticationMessage getDefaultInstanceForType() {
        return UserAuthenticationNotice.UserAuthenticationMessage.getDefaultInstance();
      }

      @java.lang.Override
      public UserAuthenticationNotice.UserAuthenticationMessage build() {
        UserAuthenticationNotice.UserAuthenticationMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public UserAuthenticationNotice.UserAuthenticationMessage buildPartial() {
        UserAuthenticationNotice.UserAuthenticationMessage result = new UserAuthenticationNotice.UserAuthenticationMessage(this);
        result.userId_ = userId_;
        result.username_ = username_;
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
        if (other instanceof UserAuthenticationNotice.UserAuthenticationMessage) {
          return mergeFrom((UserAuthenticationNotice.UserAuthenticationMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(UserAuthenticationNotice.UserAuthenticationMessage other) {
        if (other == UserAuthenticationNotice.UserAuthenticationMessage.getDefaultInstance()) return this;
        if (other.getUserId() != 0) {
          setUserId(other.getUserId());
        }
        if (!other.getUsername().isEmpty()) {
          username_ = other.username_;
          onChanged();
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
        UserAuthenticationNotice.UserAuthenticationMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (UserAuthenticationNotice.UserAuthenticationMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private int userId_ ;
      /**
       * <code>int32 userId = 1;</code>
       * @return The userId.
       */
      @java.lang.Override
      public int getUserId() {
        return userId_;
      }
      /**
       * <code>int32 userId = 1;</code>
       * @param value The userId to set.
       * @return This builder for chaining.
       */
      public Builder setUserId(int value) {
        
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 userId = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearUserId() {
        
        userId_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object username_ = "";
      /**
       * <code>string username = 2;</code>
       * @return The username.
       */
      public java.lang.String getUsername() {
        java.lang.Object ref = username_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          username_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string username = 2;</code>
       * @return The bytes for username.
       */
      public com.google.protobuf.ByteString
          getUsernameBytes() {
        java.lang.Object ref = username_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          username_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string username = 2;</code>
       * @param value The username to set.
       * @return This builder for chaining.
       */
      public Builder setUsername(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        username_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string username = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearUsername() {
        
        username_ = getDefaultInstance().getUsername();
        onChanged();
        return this;
      }
      /**
       * <code>string username = 2;</code>
       * @param value The bytes for username to set.
       * @return This builder for chaining.
       */
      public Builder setUsernameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        username_ = value;
        onChanged();
        return this;
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


      // @@protoc_insertion_point(builder_scope:UserAuthenticationMessage)
    }

    // @@protoc_insertion_point(class_scope:UserAuthenticationMessage)
    private static final UserAuthenticationNotice.UserAuthenticationMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new UserAuthenticationNotice.UserAuthenticationMessage();
    }

    public static UserAuthenticationNotice.UserAuthenticationMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<UserAuthenticationMessage>
        PARSER = new com.google.protobuf.AbstractParser<UserAuthenticationMessage>() {
      @java.lang.Override
      public UserAuthenticationMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new UserAuthenticationMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<UserAuthenticationMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<UserAuthenticationMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public UserAuthenticationNotice.UserAuthenticationMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_UserAuthenticationMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_UserAuthenticationMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\030UserAuthentication.proto\"=\n\031UserAuthen" +
      "ticationMessage\022\016\n\006userId\030\001 \001(\005\022\020\n\010usern" +
      "ame\030\002 \001(\tB\032B\030UserAuthenticationNoticeb\006p" +
      "roto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_UserAuthenticationMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_UserAuthenticationMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_UserAuthenticationMessage_descriptor,
        new java.lang.String[] { "UserId", "Username", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
