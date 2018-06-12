LOCAL_PATH:= $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SDK_VERSION := 14
LOCAL_C_INCLUDES := \
	.\
	config.h\
	gif_hash.c\
	gif_hash.h\
	gif_lib.h\
	gif_lib_private.h\

LOCAL_SRC_FILES := \
	dgif_lib.c\
	egif_lib.c\
	gifalloc.c\
	gif_err.c\
	gif_hash.c\
	quantize.c\
	gif_font.c\
	openbsd-reallocarray.c\
LOCAL_CFLAGS += -Wno-format -Wno-sign-compare -Wno-unused-parameter -DHAVE_CONFIG_H
LOCAL_SDK_VERSION := 14
LOCAL_MODULE:= libgif
include $(BUILD_SHARED_LIBRARY)
